package com.javacodegeeks.nio_tutorial.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public final class Server {

  private Server() {
    throw new IllegalStateException("Instantiation not allowed");
  }

  public static void main(final String[] args) throws Exception {
    System.out.println("Starting server...");
    try (final Selector selector = Selector.open();
        final ServerSocketChannel serverSocket = ServerSocketChannel.open(); ) {
      final InetSocketAddress hostAddress = new InetSocketAddress(Constants.HOST, Constants.PORT);
      serverSocket.bind(hostAddress);
      serverSocket.configureBlocking(false);
      serverSocket.register(selector, serverSocket.validOps(), null);

      while (true) {
        final int numSelectedKeys = selector.select();
        if (numSelectedKeys > 0) {
          handleSelectionKeys(selector.selectedKeys(), serverSocket);
        }
      }
    }
  }

  private static void handleSelectionKeys(
      final Set<SelectionKey> selectionKeys, final ServerSocketChannel serverSocket)
      throws IOException {
    assert !Objects.isNull(selectionKeys) && !Objects.isNull(serverSocket);

    final Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
    while (selectionKeyIterator.hasNext()) {
      final SelectionKey key = selectionKeyIterator.next();

      if (key.isAcceptable()) {
        acceptClientSocket(key, serverSocket);
      } else if (key.isReadable()) {
        readRequest(key);
      } else {
        System.out.println("Invalid selection key");
      }

      selectionKeyIterator.remove();
    }
  }

  private static void acceptClientSocket(
      final SelectionKey key, final ServerSocketChannel serverSocket) throws IOException {
    assert !Objects.isNull(key) && !Objects.isNull(serverSocket);

    final SocketChannel client = serverSocket.accept();
    client.configureBlocking(false);
    client.register(key.selector(), SelectionKey.OP_READ);

    System.out.println("Accepted connection from client");
  }

  private static void readRequest(final SelectionKey key) throws IOException {
    assert !Objects.isNull(key);

    final SocketChannel client = (SocketChannel) key.channel();
    final ByteBuffer buffer = ByteBuffer.allocate(Constants.CLIENT_BYTE_BUFFER_CAPACITY);

    final int bytesRead = client.read(buffer);

    if (bytesRead == -1) {
      client.close();
    } else {
      System.out.println(String.format("Request data: %s", new String(buffer.array())));
    }
  }
}
