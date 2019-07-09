package networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public final class ChargenServer {
  private static final int DEFAULT_PORT = 19;

  private ChargenServer() {
    throw new IllegalStateException("Private constructor");
  }

  private static int getPort(String... args) {
    int port;
    try {
      port = Integer.parseInt(args[0]);
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
      port = DEFAULT_PORT;
    }
    return port;
  }

  public static void main(String[] args) {
    int port = getPort(args);
    System.out.println("Listening for connections on port " + port);
    byte[] rotation = new byte[95 * 2];
    for (byte i = ' '; i <= '~'; i++) {
      rotation[i - ' '] = i;
      rotation[i + 95 - ' '] = i;
    }
    ServerSocketChannel serverChannel;
    Selector selector;
    try {
      serverChannel = ServerSocketChannel.open();
      ServerSocket ss = serverChannel.socket();
      InetSocketAddress address = new InetSocketAddress(port);
      ss.bind(address);
      serverChannel.configureBlocking(false);
      selector = Selector.open();
      serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
      return;
    }
    while (true) {
      try {
        selector.select();
      } catch (IOException ex) {
        System.err.println(ex.getMessage());
        break;
      }
      Set<SelectionKey> readyKeys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = readyKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        iterator.remove();
        try {
          if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel)key.channel();
            SocketChannel client = server.accept();
            System.out.println("Accepted connection from " + client);
            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(74);
            buffer.put(rotation, 0, 72);
            buffer.put((byte)'\r');
            buffer.put((byte)'\n');
            buffer.flip();
            SelectionKey key2 =
                client.register(selector, SelectionKey.OP_WRITE);
            key2.attach(buffer);
          } else if (key.isWritable()) {
            SocketChannel client = (SocketChannel)key.channel();
            ByteBuffer buffer = (ByteBuffer)key.attachment();
            if (!buffer.hasRemaining()) {
              // Refill the buffer with the next line
              buffer.rewind();
              // Get the old first character
              int first = buffer.get();
              // Get ready to change the data in the buffer
              buffer.rewind();
              // Find the new first characters position in rotation
              int position = first - ' ' + 1;
              // copy the data from rotation into the buffer
              buffer.put(rotation, position, 72);
              // Store a line break at the end of the buffer
              buffer.put((byte)'\r');
              buffer.put((byte)'\n');
              // Prepare the buffer for writing
              buffer.flip();
            }
            client.write(buffer);
          }
        } catch (IOException ex) {
          key.cancel();
          try {
            key.channel().close();
          } catch (IOException cex) {
            System.err.println(cex.getMessage());
          }
        }
      }
    }
  }
}
