package networking;

import java.io.IOException;
import java.net.InetSocketAddress;
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
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
      port = DEFAULT_PORT;
    }
    return port;
  }

  @SuppressWarnings("checkstyle:magicnumber")
  private static void handleChannels(SelectionKey key, Selector selector) {
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
        System.err.println("Error closing channel: " + cex.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    int port = getPort(args);

    Selector selector = null;
    try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
      InetSocketAddress address = new InetSocketAddress(port);
      serverChannel.bind(address);
      serverChannel.configureBlocking(false);
      selector = Selector.open();
      serverChannel.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("selector registered");
    } catch (IOException ex) {
      System.err.println("Error with server channel: " + ex.getMessage());
      return;
    }
    while (true) {
      try {
        System.out.println("about to select...");
        selector.select();
      } catch (IOException ex) {
        System.err.println("Error selecting channel: " + ex.getMessage());
        break;
      }
      Set<SelectionKey> readyKeys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = readyKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        iterator.remove();
        System.out.println("About to handle key...");
        handleChannels(key, selector);
      }
    }
  }
}
