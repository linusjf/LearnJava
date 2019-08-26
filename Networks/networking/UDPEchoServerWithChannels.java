package networking;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public enum UDPEchoServerWithChannels {
  ;
  public static final int PORT = 7;
  public static final int MAX_PACKET_SIZE = 65_507;

  private static int readPort(String portVal) {
    try {
      return Integer.parseInt(portVal);
    } catch (NumberFormatException nfe) {
      return PORT;
    }
  }

  public static void main(String[] args) {
    int port = args.length > 0 ? readPort(args[0]):PORT;
    try {
      DatagramChannel channel = DatagramChannel.open();
      DatagramSocket socket = channel.socket();
      SocketAddress address = new InetSocketAddress(port);
      socket.bind(address);
      ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
      while (true) {
        SocketAddress client = channel.receive(buffer);
        buffer.flip();
        channel.send(buffer, client);
        buffer.clear();
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
