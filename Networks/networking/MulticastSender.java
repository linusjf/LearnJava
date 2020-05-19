package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

public final class MulticastSender {
  private MulticastSender() {
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

  @SuppressWarnings("PMD.LawOfDemeter")
  private static byte getTTL(String... args) {
    try {
      if (args.length > (1 + 1)) return (byte) Integer.parseInt(args[2]);
    } catch (NumberFormatException e) {
      // empty catch block
    }
    return (byte) 1;
  }

  @SuppressWarnings({"PMD.AvoidLiteralsInIfCondition", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    InetAddress ia = getAddress(args);
    int port = getPort(args);
    byte ttl = getTTL(args);

    byte[] data = "Here's some multicast data\r\n".getBytes(StandardCharsets.UTF_8);
    try (MulticastSocket ms = new MulticastSocket()) {
      ms.setTimeToLive(ttl);
      ms.joinGroup(ia);
      DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
      IntStream.range(1, 10)
          .forEach(
              i -> {
                try {
                  ms.send(dp);
                } catch (IOException e) {
                  System.err.println(e);
                }
              });

      /*for (int i = 1; i < 10; i++)
      ms.send(dp);*/
      ms.leaveGroup(ia);
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
