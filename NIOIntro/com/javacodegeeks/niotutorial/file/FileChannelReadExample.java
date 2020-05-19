package com.javacodegeeks.niotutorial.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;

/**
 * Describe class <code>FileChannelReadExample</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class FileChannelReadExample implements FileChannelExample {
  private static final String INPUT_FILE_PATH = "file/input.txt";
  private static final int BYTE_BUFFER_LENGTH = 1024;

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(final String[] args) {
    System.out.println("Reading...");
    System.out.println(new FileChannelReadExample().readFile());
  }

  /**
   * Describe <code>readFile</code> method here.
   *
   * @return a <code>String</code> value
   */
  @SuppressWarnings("PMD.LawOfDemeter")
  public String readFile() {
    try (SeekableByteChannel fileChannel = createChannel(INPUT_FILE_PATH, FileOperation.READ)) {
      final ByteBuffer buffer = createBuffer();
      final StringBuilder contents = new StringBuilder();
      while (fileChannel.read(buffer) != -1) {
        contents.append(convertBytesToString(buffer));
        buffer.clear();
      }
      return contents.toString();
    } catch (IOException e) {
      throw new NIORuntimeException("Unable to read file", e);
    }
  }

  private String convertBytesToString(ByteBuffer buffer) {
    return new String(buffer.array(), StandardCharsets.UTF_8);
  }

  private ByteBuffer createBuffer() {
    return ByteBuffer.allocate(BYTE_BUFFER_LENGTH);
  }
}
