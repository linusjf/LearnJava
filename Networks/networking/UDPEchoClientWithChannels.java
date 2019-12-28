package networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public enum UDPEchoClientWithChannels {
  ;
  public static final int PORT = 7;
  private static final int LIMIT = 100;

  private static int readPort(String portVal) {
    try {
      return Integer.parseInt(portVal);
    } catch (NumberFormatException nfe) {
      return PORT;
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    String host = args.length > 0 ? args[0] : "localhost";
    int port = args.length > 1 ? readPort(args[1]) : PORT;
    SocketAddress remote = new InetSocketAddress(host, port);
    echoToServer(remote);
  }

  private static void echoToServer(SocketAddress remote) {
    try (DatagramChannel channel = DatagramChannel.open()) {
      channel.configureBlocking(false);
      channel.connect(remote);
      Selector selector = Selector.open();
      channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
      ByteBuffer buffer = ByteBuffer.allocate(4);
      echoToServer(buffer, selector, channel);
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  private static void echoToServer(ByteBuffer buffer,
                                   Selector selector,
                                   DatagramChannel channel) throws IOException {
    int n = 0;
    int numbersRead = 0;
    while (numbersRead < LIMIT) {
      // wait one minute for a connection
      selector.select(60_000);
      Set<SelectionKey> readyKeys = selector.selectedKeys();

      // All packets have been written and it doesn't look like any
      // more are will arrive from the network
      if (readyKeys.isEmpty() && n == LIMIT)
        break;
      else {
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
          SelectionKey key = iterator.next();
          iterator.remove();
          if (key.isReadable()) {
            buffer.clear();
            channel.read(buffer);
            buffer.flip();
            int echo = buffer.getInt();
            System.out.println("Read: " + echo);
            numbersRead++;
          }
          if (key.isWritable() && n < LIMIT) {
            buffer.clear();
            buffer.putInt(n);
            buffer.flip();
            channel.write(buffer);
            System.out.println("Wrote: " + n);
            n++;
          } else
            // All packets have been written;
            // switch to read-only mode
            key.interestOps(SelectionKey.OP_READ);
        }
      }
    }
    System.out.println("Echoed " + numbersRead + " out of " + LIMIT + " sent");
    System.out.println("Success rate: " + 100.0 * numbersRead / LIMIT + "%");
  }
}
