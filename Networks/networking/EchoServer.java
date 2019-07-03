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

public final class EchoServer {
  public static final int DEFAULT_PORT = 7;

  private EchoServer() {
    throw new IllegalStateException("Private constructor");
  }

  private static int getPort(String... args) {
    int port;
    try {
      port = Integer.parseInt(args[0]);
    } catch (NumberFormatException ex) {
      port = DEFAULT_PORT;
    }
    return port;
  }

  private static Selector initSelector(int port) {
    Selector selector = null;
    try (ServerSocketChannel serverChannel = ServerSocketChannel.open();
         ServerSocket ss = serverChannel.socket();) {
      InetSocketAddress address = new InetSocketAddress(port);
      ss.bind(address);
      serverChannel.configureBlocking(false);
      selector = Selector.open();
      serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
      throw new AssertionError("Unable to initialize selector", ex);
    }
    return selector;
  }

  public static void main(String[] args) {
    int port = getPort(args);
    System.out.println("Listening for connections on port " + port);

    Selector selector = initSelector(port);

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
            SelectionKey clientKey = client.register(
                selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
            ByteBuffer buffer = ByteBuffer.allocate(100);
            clientKey.attach(buffer);
          }
          if (key.isReadable()) {
            SocketChannel client = (SocketChannel)key.channel();
            ByteBuffer output = (ByteBuffer)key.attachment();
            client.read(output);
          }
          if (key.isWritable()) {
            SocketChannel client = (SocketChannel)key.channel();
            ByteBuffer output = (ByteBuffer)key.attachment();
            output.flip();
            client.write(output);
            output.compact();
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
