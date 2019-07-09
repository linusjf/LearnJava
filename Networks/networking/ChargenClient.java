package networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public final class ChargenClient {
  private static final int DEFAULT_PORT = 19;

  private ChargenClient() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java ChargenClient host [port]");
      return;
    }
    int port;
    try {
      port = Integer.parseInt(args[1]);
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
      port = DEFAULT_PORT;
    }
    try {
      SocketAddress address = new InetSocketAddress(args[0], port);
      SocketChannel client = SocketChannel.open(address);
      System.out.println("clinent readt");
      ByteBuffer buffer = ByteBuffer.allocate(74);
      WritableByteChannel out = Channels.newChannel(System.out);
      System.out.println("out ready");
      while (client.read(buffer) != -1) {
        buffer.flip();
        out.write(buffer);
        buffer.clear();
      }
    } catch (IOException ex) {
      System.err.println("Error writing to server: " + ex.getMessage());
      ex.printStackTrace();
    }
  }
}
