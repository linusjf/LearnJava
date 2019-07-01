package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class MultithreadedDaytimeServer {
  public static final int PORT = 1331;

  private MultithreadedDaytimeServer() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(PORT)) {
      while (true) {
        try {
          Socket connection = server.accept();
          Thread task = new DaytimeThread(connection);
          task.start();
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
      }
    } catch (IOException ex) {
      System.err.println("Couldn't start server");
    }
  }

  private static class DaytimeThread extends Thread {
    private Socket connection;

    DaytimeThread(Socket connection) {
      super();
      this.connection = connection;
    }

    @Override
    public void run() {
      try {
        Writer out = new OutputStreamWriter(connection.getOutputStream());
        Date now = new Date();
        SimpleDateFormat format =
            new SimpleDateFormat("yy-MM-dd hh:mm:ss Z", Locale.getDefault());
        out.write(ProcessHandle.current().pid() + " " + format.format(now)
                  + "\\r\\n");
        out.flush();
      } catch (IOException ex) {
        System.err.println(ex);
      } finally {
        try {
          connection.close();
        } catch (IOException e) {
          System.err.println(e.getMessage());
        }
      }
    }
  }
}
