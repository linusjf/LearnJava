package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public enum UDPDiscardClient {
  ;
  private static final int PORT = 9;
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    String hostname = args.length > 0 ? args[0] : "localhost";
    int port = args.length > 1 ? readPort(args[1]) : PORT;
    try (DatagramSocket theSocket = new DatagramSocket();
         BufferedReader userInput =
             new BufferedReader(new InputStreamReader(System.in, UTF_8));) {
      String theLine = userInput.readLine();
      while (!".".equals(theLine)) {
        byte[] data = theLine.getBytes(StandardCharsets.UTF_8);
        DatagramPacket theOutput = new DatagramPacket(
            data, data.length, InetAddress.getByName(hostname), port);
        theSocket.send(theOutput);
        theLine = userInput.readLine();
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  private static int readPort(String portVal) {
    try {
      return Integer.parseInt(portVal);
    } catch (NumberFormatException nfe) {
      return PORT;
    }
  }
}
