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

public final class ChargenServer {
  private static final int DEFAULT_PORT = 19;

  private ChargenServer() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static int getPort(String... args) {
    int port;
    try {
      port = Integer.parseInt(args[0]);
      System.out.printf("Using port %d: ", port);
    } catch (NumberFormatException
             | ArrayIndexOutOfBoundsException ex) {
      port = DEFAULT_PORT;
      System.out.printf(
          "Error parsing input. Using port %d: ", port);
    }
    return port;
  }

  @SuppressWarnings({"checkstyle:magicnumber",
                     "PMD.DataflowAnomalyAnalysis"})
  private static byte[] constructRotatingArray() {
    byte[] rotation = new byte[95 * 2];
    for (byte i = ' '; i <= '~'; i++) {
      rotation[i - ' '] = i;
      rotation[i + 95 - ' '] = i;
    }
    return rotation;
  }

  @SuppressWarnings({"checkstyle:magicnumber",
  "PMD.LawOfDemeter"})
  private static void handleChannels(SelectionKey key,
                                     byte[] rotation,
                                     Selector selector) {
    try {
      if (key.isAcceptable()) {
        ServerSocketChannel server =
            (ServerSocketChannel)key.channel();
        SocketChannel client = server.accept();
        System.out.println("Accepted connection from "
                           + client);
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(74);
        buffer.put(rotation, 0, 72);
        buffer.put((byte)'\r');
        buffer.put((byte)'\n');
        buffer.flip();
        SelectionKey key2 = client.register(
            selector, SelectionKey.OP_WRITE);
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
        System.err.println("Error closing channel: "
                           + cex.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    // get port value from command line
    // parameters or default value
    int port = getPort(args);
    System.out.println("Listening for connections on port "
                       + port);
    ServerSocketChannel serverChannel;
    Selector selector;
    try {
      serverChannel = ServerSocketChannel.open();
      InetSocketAddress address =
          new InetSocketAddress(port);
      serverChannel.bind(address);
      serverChannel.configureBlocking(false);
      selector = Selector.open();
      serverChannel.register(selector,
                             SelectionKey.OP_ACCEPT);
    } catch (IOException ex) {
      System.err.println(
          "Error with server socket channel: "
          + ex.getMessage());
      return;
    }

    // fill up byte array with ascii characters
    byte[] rotation = constructRotatingArray();
    while (true) {
      try {
        selector.select();
      } catch (IOException ex) {
        System.err.println("Error selecting: "
                           + ex.getMessage());
        break;
      }
      iterateOverKeys(selector,rotation);
    }
  }

  private static void iterateOverKeys(Selector selector,byte[] rotation) {
      Set<SelectionKey> readyKeys = selector.selectedKeys();
      iterateOverKeys(readyKeys,rotation,selector);
  }
  
  private static void iterateOverKeys(Set<SelectionKey> readyKeys,byte[] rotation,
      Selector selector) {
      Iterator<SelectionKey> iterator =
          readyKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        iterator.remove();
        handleChannels(key, rotation, selector);
      }
  }
}
