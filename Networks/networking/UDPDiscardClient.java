package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public enum UDPDiscardClient {
  ;
  private static final int PORT = 9;

  public static void main(String[] args) {
    String hostname = args.length > 0 ? args[0] : "localhost";
    int port = args.length > 1 ? readPort(args[1]) : PORT;
    try (DatagramSocket theSocket = new DatagramSocket()) {
      InetAddress server = InetAddress.getByName(hostname);
      BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
      while (true) {
        String theLine = userInput.readLine();
        if (".".equals(theLine)) break;
        byte[] data = theLine.getBytes();
        DatagramPacket theOutput = new DatagramPacket(data, data.length, server, port);
        theSocket.send(theOutput);
      } // end while
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
