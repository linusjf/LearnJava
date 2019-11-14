package networking;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public final class LocalPortScanner {

  private LocalPortScanner() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    for (int port = 1; port <= 65_535; port++) {
      try (ServerSocket server = new ServerSocket(); ) {
        server.bind(new InetSocketAddress(port));
      } catch (BindException ex) {
        if (ex.getMessage().startsWith("Address already in use"))
          System.err.println("There is a server on " + port + ".");
      } catch (IOException ex) {
        System.err.println("io: " + ex.getMessage());
      }
    }
  }
}
