package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public enum UDPDiscardServer {
  ;
  public static final int PORT = 9;
  public static final int MAX_PACKET_SIZE = 65_507;

  private static int readPort(String portVal) {
    try {
      return Integer.parseInt(portVal);
    } catch (NumberFormatException nfe) {
      return PORT;
    }
  }

  public static void main(String[] args) {
    int port = args.length > 0 ? readPort(args[0]) : PORT;
    byte[] buffer = new byte[MAX_PACKET_SIZE];
    System.out.println("port: " + port);
    try (DatagramSocket server = new DatagramSocket(port)) {
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      while (true) {
        try {
          server.receive(packet);
          String s =
              new String(packet.getData(), 0, packet.getLength(), "8859_1");
          System.out.println(packet.getAddress() + " at port "
                             + packet.getPort() + " says: " + s);
          // reset the length for the next packet
          packet.setLength(buffer.length);
        } catch (IOException ex) {
          System.err.println(ex);
        }
      }  // end while
    } catch (SocketException ex) {
      System.err.println(ex);
    }
  }
}
