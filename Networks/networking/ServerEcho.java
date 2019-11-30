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

  private static int getPort(String... args) {
    if (args.length > 0) {
      try {
        return Integer.parseInt(args[0]);
      } catch (NumberFormatException nfe) {
        // empty catch block
      }
    }
    return PORT;
  }

  public static void main(String... args) {
    int port = getPort(args);
    try {
      DatagramPacket dpack = new DatagramPacket(new byte[150], 150);
      DatagramSocket dsock = new DatagramSocket(port);
      receiveAndSend(dsock, dpack);
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private static void receiveAndSend(DatagramSocket dsock, DatagramPacket dpack)
      throws IOException {
    while (true) {
      dsock.receive(dpack);

      byte[] arr2 = dpack.getData();
      int packSize = dpack.getLength();
      String s2 = new String(arr2, 0, packSize);

      System.out.println(new Date() + "  " + dpack.getAddress() + " : "
                         + dpack.getPort() + " " + s2);
      dsock.send(dpack);
    }
  }
}
