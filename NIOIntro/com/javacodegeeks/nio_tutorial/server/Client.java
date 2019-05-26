package com.javacodegeeks.nio_tutorial.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public final class Client {

  private Client() {
    throw new IllegalStateException("Instantiation not allowed");
  }

  public static void main(final String[] args) {
    final InetSocketAddress hostAddress = new InetSocketAddress(Constants.HOST, Constants.PORT);

    for (int i = 0; i < 10; i++) {
      try (final SocketChannel client = SocketChannel.open(hostAddress)) {

        final ByteBuffer buffer = ByteBuffer.wrap(Constants.TEXT_FIRST_SEGMENT.getBytes());

        while (buffer.hasRemaining()) {
          client.write(buffer);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
