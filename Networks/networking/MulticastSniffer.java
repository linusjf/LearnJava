package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public final class MulticastSniffer {
  private MulticastSniffer() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static InetAddress getAddress(String... args) {
    try {
      return InetAddress.getByName(args[0]);
    } catch (IOException | SecurityException e) {
      throw new AssertionError("Invalid host or address specified", e);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static int getPort(String... args) {
    try {
      return Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      throw new AssertionError("Invalid number specified", e);
    }
  }

  public static void main(String[] args) {
    InetAddress group = getAddress(args);
    int port = getPort(args);

    try {
      MulticastSocket ms = new MulticastSocket(port);
      ms.joinGroup(group);
      while (true) {
        DatagramPacket dp = new DatagramPacket(new byte[8192], 8192);
        try {
          ms.receive(dp);
        } catch (IOException ioe) {
          break;
        }
        String s = new String(dp.getData(), "8859_1");
        System.out.println(s);
      }
      ms.leaveGroup(group);
      ms.close();
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
