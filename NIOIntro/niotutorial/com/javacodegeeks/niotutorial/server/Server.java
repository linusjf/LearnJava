package com.javacodegeeks.niotutorial.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Describe class <code>Server</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class Server {
  private Server() {
    throw new IllegalStateException("Instantiation not allowed");
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(final String[] args) {
    System.out.println("Starting server...");
    try (Selector selector = Selector.open();
         ServerSocketChannel serverSocket = ServerSocketChannel.open();) {
      final InetSocketAddress hostAddress =
          new InetSocketAddress(Constants.HOST, Constants.PORT);
      serverSocket.bind(hostAddress);
      serverSocket.configureBlocking(false);
      serverSocket.register(selector, serverSocket.validOps(), null);

      while (true) {
        final int numSelectedKeys = selector.select();
        if (numSelectedKeys > 0) {
          handleSelectionKeys(selector.selectedKeys(), serverSocket);
        }
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private static void handleSelectionKeys(final Set<SelectionKey> selectionKeys,
                                          final ServerSocketChannel
                                              serverSocket) throws IOException {
    if (Objects.isNull(selectionKeys) || Objects.isNull(serverSocket))
      throw new AssertionError("selectionKeys and/or serverSocket null.");

    final Iterator<SelectionKey> selectionKeyIterator =
        selectionKeys.iterator();
    while (selectionKeyIterator.hasNext()) {
      final SelectionKey key = selectionKeyIterator.next();

      handleKey(key, serverSocket);
      selectionKeyIterator.remove();
    }
  }

  private static void handleKey(SelectionKey key,
                                ServerSocketChannel serverSocket)
      throws IOException {
    if (key.isAcceptable())
      acceptClientSocket(key, serverSocket);
    else if (key.isReadable())
      readRequest(key);
    else
      System.out.println("Invalid selection key");
  }

  private static void acceptClientSocket(final SelectionKey key,
                                         final ServerSocketChannel serverSocket)
      throws IOException {
    if (Objects.isNull(key) || Objects.isNull(serverSocket))
      throw new AssertionError("key and/or serverSocket null.");

    final SocketChannel client = serverSocket.accept();
    configureAndRegister(client, key);
    System.out.println("Accepted connection from client");
  }

  private static void configureAndRegister(SocketChannel client,
                                           SelectionKey key)
      throws IOException {
    client.configureBlocking(false);
    client.register(key.selector(), SelectionKey.OP_READ);
  }

  private static void readRequest(final SelectionKey key) throws IOException {
    if (Objects.isNull(key))
      throw new AssertionError("key null.");

    final SocketChannel client = (SocketChannel)key.channel();
    final ByteBuffer buffer =
        ByteBuffer.allocate(Constants.CLIENT_BYTE_BUFFER_CAPACITY);
    processBuffer(buffer, client);
  }

  private static void processBuffer(ByteBuffer buffer, SocketChannel client)
      throws IOException {
    final int bytesRead = client.read(buffer);

    if (bytesRead == -1)
      client.close();
    else {
      System.out.println(
          String.format("Request data: %s",
                        new String(buffer.array(), StandardCharsets.UTF_8)));
    }
  }
}
