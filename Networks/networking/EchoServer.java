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

  @SuppressWarnings("PMD.LawOfDemeter")
  private static int getPort(String... args) {
    int port;
    try {
      port = Integer.parseInt(args[0]);
      System.out.printf("Using port: %d%n", port);
    } catch (NumberFormatException
             | ArrayIndexOutOfBoundsException ex) {
      port = DEFAULT_PORT;
      System.err.printf("Using port: %d%n", port);
    }
    return port;
  }

  @SuppressWarnings({"checkstyle:magicnumber","PMD.LawOfDemeter"})
  private static void handleChannels(SelectionKey key,
                                     Selector selector) {
    try {
      if (key.isAcceptable()) {
        ServerSocketChannel server =
            (ServerSocketChannel)key.channel();
        SocketChannel client = server.accept();
        System.out.println("Accepted connection from "
                           + client);
        client.configureBlocking(false);
        SelectionKey clientKey = client.register(
            selector,
            SelectionKey.OP_WRITE | SelectionKey.OP_READ);
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
        System.err.println("Error closing channel: "
                           + cex.getMessage());
      }
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try (ServerSocketChannel serverChannel =
             ServerSocketChannel.open()) {
      InetSocketAddress address =
          new InetSocketAddress(getPort(args));
      serverChannel.bind(address);
      serverChannel.configureBlocking(false);
      Selector selector = Selector.open();
      serverChannel.register(selector,
                             SelectionKey.OP_ACCEPT);
      System.out.println("selector registered");
      while (true) {
        System.out.println("about to select...");
        selector.select();

        Set<SelectionKey> readyKeys =
            selector.selectedKeys();
        Iterator<SelectionKey> iterator =
            readyKeys.iterator();
        while (iterator.hasNext()) {
          SelectionKey key = iterator.next();
          iterator.remove();
          System.out.println("About to handle key...");
          handleChannels(key, selector);
        }
      }
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }
}
