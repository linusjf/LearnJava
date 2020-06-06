package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class MultithreadedDaytimeServer {
  public static final int PORT = 1331;

  private MultithreadedDaytimeServer() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(PORT)) {
      while (true) {
        try {
          Socket connection = server.accept();
          Thread task = new Thread(new DaytimeTask(connection));
          task.start();
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
      }
    } catch (IOException ex) {
      System.err.println("Couldn't start server");
    }
  }
}
