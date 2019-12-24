package networking;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import logging.FormatLogger;

public class JHttp {
  private static final FormatLogger LOGGER =
      new FormatLogger(Logger.getLogger(JHttp.class.getCanonicalName()));

  private static final int NUM_THREADS = 50;
  private static final String INDEX_FILE = "index.html";
  private final File rootDirectory;
  private final int port;

  public JHttp(File rootDirectory, int port) throws IOException {
    if (!rootDirectory.isDirectory()) {
      throw new IOException(rootDirectory + " does not exist as a directory");
    }
    this.rootDirectory = rootDirectory;
    this.port = port;
  }

  private void acceptAndSubmit(ServerSocket server, ExecutorService pool) {
    while (true) {
      try {
        Socket request = server.accept();
        Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, request);
        pool.submit(r);
      } catch (IOException ex) {
        LOGGER.warning("Error accepting connection: %s", ex.getMessage());
      }
    }
  }

  public void start() throws IOException {
    try (ServerSocket server = new ServerSocket(port)) {
      LOGGER.info("Accepting connections on port %d.", server.getLocalPort());
      LOGGER.info("Document Root: %s", rootDirectory);
      ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
      acceptAndSubmit(server, pool);
    }
  }

  private static File getRoot(String... args) {
    return new File(args[0]);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static int getPort(String... args) {
    try {
      int port = Integer.parseInt(args[1]);
      if (port < 0 || port > 65_535)
        port = 80;
      return port;
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
      return 80;
    }
  }

  public static void main(String[] args) {
    // get the Document root
    File docroot = getRoot(args);

    // set the port to listen on
    int port = getPort(args);

    try {
      JHttp webserver = new JHttp(docroot, port);
      webserver.start();
    } catch (IOException ex) {
      LOGGER.severe("Server could not start: %s", ex.getMessage());
    }
  }
}
