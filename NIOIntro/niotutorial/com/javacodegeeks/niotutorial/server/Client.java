package com.javacodegeeks.niotutorial.server;

import static com.javacodegeeks.niotutorial.server.Constants.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * Describe class <code>Client</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class Client {
  static final InetSocketAddress HOSTADDRESS =
      new InetSocketAddress(HOST, PORT);

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

    for (int i = 0; i < 10; i++) {
      try (SocketChannel client = SocketChannel.open(HOSTADDRESS)) {
        final ByteBuffer buffer = ByteBuffer.wrap(
            TEXT_FIRST_SEGMENT.getBytes(StandardCharsets.UTF_8));
        writeToBuffer(client, buffer, i);
      } catch (IOException e) {
        System.err.println("Error with client writing " + e.getMessage());
      }
    }
  }

  private static void writeToBuffer(SocketChannel client,
                                    ByteBuffer buffer,
                                    int i) throws IOException {
    while (buffer.hasRemaining()) {
      client.write(buffer);
      System.out.println("Written " + (i + 1) + " : "
                         + convertBytesToString(buffer.array()));
    }
  }

  private static String convertBytesToString(byte[] bytes) {
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
