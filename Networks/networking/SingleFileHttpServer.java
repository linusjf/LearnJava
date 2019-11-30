package networking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleFileHttpServer {
  private static final Logger LOGGER = Logger.getLogger("SingleFileHTTPServer");

  private final byte[] content;
  private final byte[] header;
  private final int port;
  private final String encoding;

  public SingleFileHttpServer(String data,
                              String encoding,
                              String mimeType,
                              int port) throws UnsupportedEncodingException {
    this(data.getBytes(encoding), encoding, mimeType, port);
  }

  public SingleFileHttpServer(byte[] data,
                              String encoding,
                              String mimeType,
                              int port) {
    this.content = data.clone();
    this.port = port;
    this.encoding = encoding;
    String headerString = "HTTP/1.0 200 OK\r\n"
                          + "Server: OneFile 2.0\r\n"
                          + "Content-length: " + this.content.length + "\r\n"
                          + "Content-type: " + mimeType
                          + "; charset=" + encoding + "\r\n\r\n";
    this.header = headerString.getBytes(Charset.forName("US-ASCII"));
  }

  private void acceptAndSubmit(ExecutorService pool, ServerSocket server) {
    while (true) {
      try {
        Socket connection = server.accept();
        pool.submit(new HttpHandler(connection));
      } catch (IOException ex) {
        if (LOGGER.isLoggable(Level.WARNING))
          LOGGER.log(Level.WARNING, "Exception accepting connection", ex);
      }
    }
  }

  public void start() {
    try (ServerSocket server = new ServerSocket(this.port)) {
      if (LOGGER.isLoggable(Level.INFO)) {
        LOGGER.info("Accepting connections on port " + server.getLocalPort());
        LOGGER.info("Data to be sent:");
        LOGGER.info(new String(this.content, encoding));
      }
      ExecutorService pool = Executors.newFixedThreadPool(100);
      acceptAndSubmit(pool, server);
    } catch (IOException ex) {
      if (LOGGER.isLoggable(Level.WARNING))
        LOGGER.log(Level.WARNING, "Exception accepting connection", ex);
    }
  }

  private static int getPort(String... args) {
    try {
      if (args.length > 1) {
        int port = Integer.parseInt(args[1]);
        if (port < 1 || port > 65_535)
          port = 80;
        return port;
      }
    } catch (NumberFormatException ex) {
      // empty catch block
    }
    return 80;
  }

  private static String getEncoding(String... args) {
    if (args.length > (1 + 1))
      return args[2];
    return "UTF-8";
  }

  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  public static void main(String[] args) {
    // set the port to listen on
    int port = getPort(args);
    String encoding = getEncoding(args);

    try {
      Path path = Paths.get(args[0]);
      byte[] data = Files.readAllBytes(path);
      String contentType =
          URLConnection.getFileNameMap().getContentTypeFor(args[0]);
      SingleFileHttpServer server =
          new SingleFileHttpServer(data, encoding, contentType, port);
      server.start();
    } catch (ArrayIndexOutOfBoundsException ex) {
      System.out.println(
          "Usage: java SingleFileHttpServer filename port encoding");
    } catch (IOException ex) {
      if (LOGGER.isLoggable(Level.SEVERE))
        LOGGER.severe(ex.getMessage());
    }
  }

  private class HttpHandler implements Callable<Void> {
    private final Socket connection;

    HttpHandler(Socket connection) {
      this.connection = connection;
    }

    @Override
    public Void call() throws IOException {
      try (OutputStream out =
               new BufferedOutputStream(connection.getOutputStream());
           InputStream in =
               new BufferedInputStream(connection.getInputStream());) {
        // read the first line only; that's all we need
        StringBuilder request = new StringBuilder(80);
        while (true) {
          int c = in.read();
          if (c == '\r' || c == '\n' || c == -1)
            break;
          request.append((char)c);
        }

        // If this is HTTP/1.0 or later send a MIME header
        if (request.toString().indexOf("HTTP/") != -1) {
          out.write(header);
        }
        out.write(content);
        out.flush();
      } catch (IOException ex) {
        if (LOGGER.isLoggable(Level.WARNING))
          LOGGER.log(Level.WARNING, "Error writing to client", ex);
      } finally {
        connection.close();
      }
      return null;
    }
  }
}
