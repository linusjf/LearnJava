package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public final class ServerEcho {
  private static final int PORT = 7;

  private ServerEcho() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    int port = PORT;
    if (args.length > 0) {
      try {
        port = Integer.parseInt(args[0]);
      } catch (NumberFormatException nfe) {
        port = PORT;
      }
    }
    try {
      DatagramSocket dsock = new DatagramSocket(port);
      byte[] arr1 = new byte[150];
      DatagramPacket dpack = new DatagramPacket(arr1, arr1.length);

      while (true) {
        dsock.receive(dpack);

        byte[] arr2 = dpack.getData();
        int packSize = dpack.getLength();
        String s2 = new String(arr2, 0, packSize);

        System.out.println(new Date() + "  " + dpack.getAddress() + " : "
                           + dpack.getPort() + " " + s2);
        dsock.send(dpack);
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
