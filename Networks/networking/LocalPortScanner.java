package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.BindException;

public class LocalPortScanner {

  public static void main(String[] args) {
    for (int port = 1; port <= 65535; port++) {
      try (ServerSocket server = new ServerSocket(port);
          ){
      } catch (BindException ex) {
if (ex.getMessage()
    .startsWith("Address already in use"))
  System.err.println("There is a server on "
      + port + ".");
      }
       catch (IOException ex) {
System.err.println("io: " + ex.getMessage());
      }
    }
  }
}
