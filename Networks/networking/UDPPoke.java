package networking;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Base64;

public class UDPPoke {
  private int bufferSize;  // in bytes
  private int timeout;     // in milliseconds
  private InetAddress host;
  private int port;

  public UDPPoke(InetAddress host, int port, int bufferSize, int timeout) {
    this.bufferSize = bufferSize;
    this.host = host;
    if (port < 1 || port > 65_535) {
      throw new IllegalArgumentException("Port out of range");
    }
    this.port = port;
    this.timeout = timeout;
  }

  public UDPPoke(InetAddress host, int port, int bufferSize) {
    this(host, port, bufferSize, 30_000);
  }

  public UDPPoke(InetAddress host, int port) {
    this(host, port, 8192, 30_000);
  }

  public byte[] poke() {
    try (DatagramSocket socket = new DatagramSocket(0)) {
      DatagramPacket outgoing = new DatagramPacket(new byte[1], 1, host, port);
      socket.connect(host, port);
      socket.setSoTimeout(timeout);
      socket.send(outgoing);
      DatagramPacket incoming =
          new DatagramPacket(new byte[bufferSize], bufferSize);
      // next line blocks until the response is received
      socket.receive(incoming);
      int numBytes = incoming.getLength();
      byte[] response = new byte[numBytes];
      System.arraycopy(incoming.getData(), 0, response, 0, numBytes);
      return response;
    } catch (IOException ex) {
      return null;
    }
  }

  public static void main(String[] args) {
    InetAddress host;
    int port = 0;
    try {
      host = InetAddress.getByName(args[0]);
      port = Integer.parseInt(args[1]);
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException
             | UnknownHostException ex) {
      System.out.println("Usage: java UDPPoke host port");
      return;
    }
      UDPPoke poker = new UDPPoke(host, port);
      byte[] response = poker.poke();
      if (response == null) {
        System.out.println("No response within allotted time");
        return;
      }
System.out.println(Base64.getEncoder().encodeToString(response));
StringBuilder sb = new StringBuilder(8);
    for (byte b : response) {
        sb.append(String.format("%02X ", b));
    }
    System.out.println(sb.toString());
  }
}
