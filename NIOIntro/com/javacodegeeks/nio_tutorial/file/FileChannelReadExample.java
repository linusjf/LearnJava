package com.javacodegeeks.nio_tutorial.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public final class FileChannelReadExample implements FileChannelExample {

  private static final String INPUT_FILE_PATH = "file/input.txt";
  private static final int BYTE_BUFFER_LENGTH = 1024;

  public static void main(final String[] args) {
    System.out.println(new FileChannelReadExample().readFile());
  }

  public String readFile() {
    final StringBuilder contents = new StringBuilder();
    try (final FileChannel fileChannel = createChannel(INPUT_FILE_PATH, FileOperation.READ)) {

      final ByteBuffer buffer = createBuffer();
      while (fileChannel.read(buffer) != -1) {

        contents.append(new String(buffer.array()));
        buffer.clear();
      }
    } catch (IOException e) {
      throw new RuntimeException("Unable to read file", e);
    }

    return contents.toString();
  }

  private ByteBuffer createBuffer() {
    return ByteBuffer.allocate(BYTE_BUFFER_LENGTH);
  }
}
