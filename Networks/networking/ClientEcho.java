package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public final class ClientEcho {
  private static final int PORT = 7;
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private ClientEcho() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void main(String... args) {
    int port = PORT;
    if (args.length > 0) {
      try {
        port = Integer.parseInt(args[0]);
        System.out.printf("Using %d.%n", args[1], port);
      } catch (NumberFormatException nfe) {
        port = PORT;
        System.err.printf(
            "Invalid input %s for port. Using %d.%n", args[1], port);
      }
    }
    try {
      InetAddress add = InetAddress.getByName("localhost");
      try (DatagramSocket dsock = new DatagramSocket();
           BufferedReader stdIn =
               new BufferedReader(new InputStreamReader(System.in, UTF_8));) {
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
          byte[] arr = userInput.getBytes(StandardCharsets.UTF_8);
          DatagramPacket dpack = new DatagramPacket(arr, arr.length, add, port);
          dsock.send(dpack);

          // send the packet
          dsock.receive(dpack);

          // receive the packet
          System.out.println(
              "echo: " + new String(dpack.getData(), StandardCharsets.UTF_8));
        }
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
