package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import logging.FormatLogger;

public final class LoggingDaytimeServer {
  public static final int PORT = 13;

  private static final FormatLogger AUDIT_LOGGER =
      new FormatLogger(Logger.getLogger("requests"));

  private static final FormatLogger ERROR_LOGGER =
      new FormatLogger(Logger.getLogger("errors"));

  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private LoggingDaytimeServer() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static int getPort(String... args) {
    try {
      return Integer.parseInt(args[0]);
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) {
      return PORT;
    }
  }

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(getPort(args))) {
      ExecutorService pool = Executors.newFixedThreadPool(50);
      acceptAndSubmit(pool, server);
    } catch (IOException ex) {
      ERROR_LOGGER.severe("Couldn't start server: %s", ex.getMessage());
    }
  }

  private static void acceptAndSubmit(ExecutorService pool,
                                      ServerSocket server) {
    while (true) {
      try {
        Socket connection = server.accept();
        Callable<Void> task = new DaytimeTask(connection);
        pool.submit(task);
      } catch (IOException ex) {
        ERROR_LOGGER.severe("accept error %s", ex.getMessage());
      }
    }
  }

  private static class DaytimeTask implements Callable<Void> {
    private final Socket connection;

    DaytimeTask(Socket connection) {
      this.connection = connection;
    }

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public Void call() {
      try {
        Date now = new Date();

        // write the log entry first in case the client disconnects
        AUDIT_LOGGER.info(
            "%s %s", (Object)now, (Object)connection.getRemoteSocketAddress());
        try (Writer out =
                 new OutputStreamWriter(connection.getOutputStream(), UTF_8);
             connection;) {
          SimpleDateFormat format =
              new SimpleDateFormat("yy-MM-dd hh:mm:ss Z", Locale.getDefault());
          out.write(ProcessHandle.current().pid() + " " + format.format(now)
                    + "\\r\\n");
          out.flush();
        }
      } catch (IOException ex) {
        AUDIT_LOGGER.warning(ex.getMessage());
      }
      return null;
    }
  }
}
