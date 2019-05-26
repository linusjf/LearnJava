package com.javacodegeeks.nio_tutorial.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteExample implements FileChannelExample {

  private static final String OUTPUT_FILE_PATH = "file/output.txt";
  private static final String CONTENTS =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus efficitur auctor risus, non tristique erat finibus nec. Pellentesque lacus ante, volutpat at purus in, mollis porttitor est. "
          + "In hac habitasse platea dictumst. Nulla vehicula lacus dapibus semper eleifend. Morbi eget malesuada lorem. Mauris mattis quam vel turpis mollis rutrum. Etiam hendrerit tortor sed purus congue volutpat. Aenean tellus nunc, "
          + "vestibulum sed nunc in, faucibus posuere quam. Pellentesque a metus sit amet dolor sollicitudin ultricies sed a felis.";

  public static void main(final String[] args) {
    new FileChannelWriteExample().writeFile();
  }

  public void writeFile() {
    try (final FileChannel fileChannel = createChannel(OUTPUT_FILE_PATH, FileOperation.WRITE)) {

      final ByteBuffer buffer = createBuffer(CONTENTS.getBytes().length);
      buffer.put(CONTENTS.getBytes());
      buffer.flip();

      while (buffer.hasRemaining()) {
        fileChannel.write(buffer);
      }
    } catch (IOException e) {
      throw new RuntimeException("Unable to write to file", e);
    }
  }

  private ByteBuffer createBuffer(final int length) {
    return ByteBuffer.allocate(length);
  }
}
