package networking;

import java.net.DatagramSocket;
import java.net.SocketException;

public enum UDPPortScanner {
  ;

  public static void main(String[] args) {
    for (int port = 1024; port <= 65_535; port++) {
      try {
        // the next line will fail and drop into the catch block if
        // there is already a server running on port i
        DatagramSocket server = new DatagramSocket(port);
        server.close();
      } catch (SocketException ex) {
        System.out.println("There is a server on port "
                           + port + ".");
      }
    }
  }
}
