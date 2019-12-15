package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class PooledDaytimeServer {
  public static final int PORT = 3131;

  private PooledDaytimeServer() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(PORT)) {
      ExecutorService pool = Executors.newFixedThreadPool(50);
      acceptAndSubmit(pool, server);
    } catch (IOException ex) {
      System.err.println("Couldn't start server");
    }
  }

  private static void acceptAndSubmit(
    ExecutorService pool,
    ServerSocket server
  ) {
    while (true) {
      try {
        Socket connection = server.accept();
        pool.submit((Callable<Void>) new DaytimeTask(connection));
      } catch (IOException ex) {
        System.err.println(ex.getMessage());
      }
    }
  }
}
