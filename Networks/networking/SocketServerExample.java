package networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SocketServerExample {
  private Selector selector;
  private final Map<SocketChannel, List<?>> dataMapper;
  private final InetSocketAddress listenAddress;

  public SocketServerExample(String address, int port) throws IOException {
    listenAddress = new InetSocketAddress(address, port);
    dataMapper = new HashMap<>();
  }

  public static void main(String[] args) {
    Runnable server = new Runnable() {
      @Override
      public void run() {
        try {
          new SocketServerExample("localhost", 8090).startServer();
        } catch (IOException e) {
          System.out.println("Error running server: " + e.getMessage());
        }
      }
    };

    Runnable client = new Runnable() {
      @Override
      public void run() {
        try {
          new SocketClientExample().startClient();
        } catch (IOException | InterruptedException e) {
          System.out.println("Error connecting to  server: " + e.getMessage());
        }
      }
    };
    new Thread(server).start();
    new Thread(client, "client-A").start();
    new Thread(client, "client-B").start();
  }

  // create server channel
  private void startServer() throws IOException {
    this.selector = Selector.open();
    ServerSocketChannel serverChannel = ServerSocketChannel.open();
    serverChannel.configureBlocking(false);

    // retrieve server socket and bind to port
    serverChannel.bind(listenAddress);
    serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);

    System.out.println("Server started...");

    while (true) {
      // wait for events
      this.selector.select();

      System.out.println("Selected...");

      // work on selected keys
      Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
      while (keys.hasNext()) {
        SelectionKey key = keys.next();

        // this is necessary to prevent the same key from coming up
        // again the next time around.
        keys.remove();

        if (!key.isValid()) {
          continue;
        }

        if (key.isAcceptable()) {
          this.accept(key);
        } else if (key.isReadable()) {
          this.read(key);
        }
      }
    }
  }

  // accept a connection made to this channel's socket
  private void accept(SelectionKey key) throws IOException {
    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
    SocketChannel channel = serverChannel.accept();
    channel.configureBlocking(false);
    Socket socket = channel.socket();
    SocketAddress remoteAddr = socket.getRemoteSocketAddress();
    System.out.println("Connected to: " + remoteAddr);

    // register channel with selector for further IO
    dataMapper.put(channel, new ArrayList<>());
    channel.register(this.selector, SelectionKey.OP_READ);
  }

  // read from the socket channel
  private void read(SelectionKey key) throws IOException {
    SocketChannel channel = (SocketChannel) key.channel();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    int numRead = channel.read(buffer);

    if (numRead == -1) {
      this.dataMapper.remove(channel);
      Socket socket = channel.socket();
      SocketAddress remoteAddr = socket.getRemoteSocketAddress();
      System.out.println("Connection closed by client: " + remoteAddr);
      channel.close();
      key.cancel();
      return;
    }

    byte[] data = new byte[numRead];
    System.arraycopy(buffer.array(), 0, data, 0, numRead);
    System.out.println("Got: " + new String(data, StandardCharsets.UTF_8));
  }
}
