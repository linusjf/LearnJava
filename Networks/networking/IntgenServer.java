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

public final class IntgenServer {
  private static final int DEFAULT_PORT = 1919;

  private IntgenServer() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void handleKey(SelectionKey key, Selector selector) {
    try {
      if (key.isAcceptable()) {
        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        SocketChannel client = server.accept();
        System.out.println("Accepted connection from " + client);
        client.configureBlocking(false);
        SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
        ByteBuffer output = ByteBuffer.allocate(4);
        output.putInt(0);
        output.flip();
        key2.attach(output);
      } else if (key.isWritable()) {
        SocketChannel client = (SocketChannel)key.channel();
        ByteBuffer output = (ByteBuffer)key.attachment();
        if (!output.hasRemaining()) {
          output.rewind();
          int value = output.getInt();
          output.clear();
          output.putInt(value + 1);
          output.flip();
        }
        client.write(output);
      }
    } catch (IOException exception) {
      key.cancel();
      try {
        key.channel().close();
      } catch (IOException cex) {
        System.err.println(cex);
      }
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static int getPort(String... args) {
    try {
      return Integer.parseInt(args[0]);
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
      return DEFAULT_PORT;
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    int port = getPort();

    System.out.println("Listening for connections on port " + port);
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
    } catch (IOException e) {
      System.err.println(e);
      return;
    }
    while (true) {
      try {
        selector.select();
      } catch (IOException exc) {
        System.out.println(exc);
        break;
      }
      Set<SelectionKey> readyKeys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = readyKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        iterator.remove();
        handleKey(key, selector);
      }
    }
  }
}
