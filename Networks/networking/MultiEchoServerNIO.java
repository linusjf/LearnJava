package networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class MultiEchoServerNIO {
  private static ServerSocketChannel serverSocketChannel;
  private static final int PORT = 1234;
  private static Selector selector;

  private MultiEchoServerNIO() {
    throw new IllegalStateException("Private constructor.");
  }

  /*
    Above Selector used both for detecting new
    connections (on the ServerSocketChannel) and for
    detecting incoming data from existing connections
    (on the SocketChannel).
  */
  public static void main(String[] args) {
    System.out.println("Opening port…\n");
    try {
      serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(false);
      ServerSocket serverSocket = serverSocketChannel.socket();

      /*
                ServerSocketChannel created before
                ServerSocket largely in order to configure
                latter as a non-blocking socket by calling
                the configureBlocking method of the
                ServerSocketChannel with argument of 'false'.
                (ServerSocket will have a ServerSocketChannel
                only if latter is created first.)
      */
      InetSocketAddress netAddress = new InetSocketAddress(PORT);

      // Bind socket to port…
      serverSocket.bind(netAddress);

      // Create a new Selector object for detecting
      // input from channels…
      selector = Selector.open();

      // Register ServerSocketChannel with Selector
      // for receiving incoming connections…
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    } catch (IOException ioEx) {
      System.err.println(ioEx);
      throw new AssertionError(ioEx.getMessage(), ioEx);
    }
    processConnections();
  }

  private static void processConnections() {
    while (true) {
      try {
        // Get number of events (new connection(s)
        // and/or data transmissions from existing
        // connection(s))…
        int numKeys = selector.select();
        if (numKeys > 0) {
          // Extract event(s) that have been
          // triggered …
          Set<SelectionKey> eventKeys = selector.selectedKeys();

          // Set up Iterator to cycle though set
          // of events…
          Set<SelectionKey> copyEventKeys = new HashSet<>(eventKeys);
          Iterator<SelectionKey> keyCycler = copyEventKeys.iterator();

          while (keyCycler.hasNext()) {
            SelectionKey key = keyCycler.next();

            // Retrieve set of ready ops for
            // this key (as a bit pattern)…
            int keyOps = key.readyOps();
            if ((keyOps & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
              // New connection.
              acceptConnection(key);
              continue;
            }
            if ((keyOps & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
              // Data from existing client.
              acceptData(key);
            }
          }
        }
      } catch (IOException | ConcurrentModificationException ioEx) {
        System.err.println(ioEx);
        throw new AssertionError(ioEx.getMessage(), ioEx);
      }
    }
  }

  private static void acceptConnection(SelectionKey key) throws IOException {
    // Accept incoming connection and add to list.
    SocketChannel socketChannel = serverSocketChannel.accept();
    socketChannel.configureBlocking(false);
    Socket socket = socketChannel.socket();
    System.out.println("Connection on " + socket + ".");

    // Register SocketChannel for receiving data…
    socketChannel.register(selector, SelectionKey.OP_READ);

    // Avoid re-processing this event as though it
    // were a new one (next time through loop)…
    selector.selectedKeys().remove(key);
  }

  private static void acceptData(SelectionKey key) throws IOException {
    // Accept data from existing connection.
    ByteBuffer buffer = ByteBuffer.allocate(2048);

    // Above used for reading/writing data from/to
    // SocketChannel.
    SocketChannel socketChannel = (SocketChannel) key.channel();
    buffer.clear();
    int numBytes = socketChannel.read(buffer);
    System.out.println(numBytes + " bytes read.");
    Socket socket = socketChannel.socket();
    if (numBytes == -1) {
      // OP_READ event also triggered by closure of
      // connection or error of some kind. In either
      // case, numBytes = -1.
      // Request that registration of this key's
      // channel with its selector be cancelled…
      key.cancel();
      System.out.println("\nClosing socket " + socket + "…");
      closeSocket(socket);
    } else {
      try {
        /*
                      Reset buffer pointer to start of buffer,
                      prior to reading buffer's contents and
                      writing them to the SocketChannel…
        */
        buffer.flip();
        while (buffer.remaining() > 0) socketChannel.write(buffer);
      } catch (IOException ioEx) {
        System.out.println("\nClosing socket " + socket + "…");
        closeSocket(socket);
      }
    }

    // Remove this event, to avoid re-processing it
    // as though it were a new one…
    selector.selectedKeys().remove(key);
  }

  private static void closeSocket(Socket socket) {
    try {
      if (socket != null)
        socket.close();
    } catch (IOException ioEx) {
      System.out.println("*** Unable to close socket! ***");
    }
  }
}
