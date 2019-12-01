package networking;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public enum UDPDiscardServerWithChannels {
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
    try {
      DatagramChannel channel = DatagramChannel.open();
      DatagramSocket socket = channel.socket();
      SocketAddress address = new InetSocketAddress(port);
      socket.bind(address);
      ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
      while (true) {
        readAndPrintClient(buffer, channel);
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  private static void readAndPrintClient(ByteBuffer buffer,
                                         DatagramChannel channel)
      throws IOException {
    SocketAddress client = channel.receive(buffer);
    buffer.flip();
    System.out.print(client + " says: ");
    while (buffer.hasRemaining())
      System.out.write(buffer.get());
    System.out.println();
    buffer.clear();
  }
}
