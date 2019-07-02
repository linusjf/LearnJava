package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingDaytimeServer {

  public static final int PORT = 13;

  private static final Logger auditLogger = Logger.getLogger("requests");

  private static final Logger errorLogger = Logger.getLogger("errors");

  public static void main(String[] args) {
    int port;
    try {
      port = Integer.parseInt(args[0]);
    } catch (NumberFormatException nfe) {
      port = PORT;
    }

    ExecutorService pool = Executors.newFixedThreadPool(50);
    try (ServerSocket server = new ServerSocket(port)) {
      while (true) {
        try {
          Socket connection = server.accept();
          Callable<Void> task = new DaytimeTask(connection);
          pool.submit(task);
        } catch (IOException ex) {
          errorLogger.log(Level.SEVERE, "accept error", ex);
        } catch (RuntimeException ex) {
          errorLogger.log(
              Level.SEVERE, "unexpected error " + ex.getMessage(), ex);
        }
      }
    } catch (IOException ex) {
      errorLogger.log(Level.SEVERE, "Couldn't start server", ex);
    } catch (RuntimeException ex) {
      errorLogger.log(
          Level.SEVERE, "Couldn't start server: " + ex.getMessage(), ex);
    }
  }

  private static class DaytimeTask implements Callable<Void> {

    private Socket connection;

    DaytimeTask(Socket connection) {
      this.connection = connection;
    }

    @Override
    public Void call() {
      try {
        Date now = new Date();
        // write the log entry first in case the client disconnects
        auditLogger.info(now + " " + connection.getRemoteSocketAddress());
        Writer out = new OutputStreamWriter(connection.getOutputStream());
        SimpleDateFormat format =
            new SimpleDateFormat("yy-MM-dd hh:mm:ss Z", Locale.getDefault());
        out.write(ProcessHandle.current().pid() + " " + format.format(now)
                  + "\\r\\n");
        out.flush();
      } catch (IOException ex) {
        // client disconnected; ignore;
      } finally {
        try {
          connection.close();
        } catch (IOException ex) {
          // ignore;
        }
      }
      return null;
    }
  }
}
