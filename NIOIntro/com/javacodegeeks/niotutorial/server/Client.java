package com.javacodegeeks.niotutorial.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Describe class <code>Client</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class Client {
  private Client() {
    throw new IllegalStateException("Instantiation not allowed");
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings("checkstyle:magicnumber")
  public static void main(final String[] args) {
    System.out.println("Starting client...");
    final InetSocketAddress hostAddress = new InetSocketAddress(Constants.HOST, Constants.PORT);

    for (int i = 0; i < 10; i++) {
      try (SocketChannel client = SocketChannel.open(hostAddress)) {
        final ByteBuffer buffer = ByteBuffer.wrap(Constants.TEXT_FIRST_SEGMENT.getBytes());

        while (buffer.hasRemaining()) {
          client.write(buffer);
          System.out.println("Written " + (i + 1) + " : " + convertBytesToString(buffer.array()));
        }
      } catch (IOException e) {
        System.err.println("Error with client writing " + e.getMessage());
      }
    }
  }

  private static String convertBytesToString(byte[] bytes) {
    return new String(bytes);
  }
}
