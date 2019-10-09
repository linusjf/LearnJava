package com.javacodegeeks.niotutorial.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

/**
 * Describe class <code>FileChannelWriteExample</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class FileChannelWriteExample implements FileChannelExample {
  private static final String OUTPUT_FILE_PATH = "file/output.txt";
  private static final String CONTENTS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
    " Phasellus efficitur auctor risus, non tristique erat finibus nec." +
    " Pellentesque lacus ante, volutpat at purus in, mollis porttitor est. " +
    "In hac habitasse platea dictumst. " +
    "Nulla vehicula lacus dapibus semper eleifend." +
    " Morbi eget malesuada lorem. Mauris mattis quam vel turpis mollis rutrum." +
    " Etiam hendrerit tortor sed purus congue volutpat." +
    " Aenean tellus nunc, " +
    "vestibulum sed nunc in, faucibus posuere quam." +
    " Pellentesque a metus sit amet dolor sollicitudin ultricies sed a felis.";

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(final String[] args) {
    System.out.println("Writing...");
    new FileChannelWriteExample().writeFile();
  }

  /** Describe <code>writeFile</code> method here. */
  public void writeFile() {
    try (
      SeekableByteChannel fileChannel = createChannel(
        OUTPUT_FILE_PATH,
        FileOperation.WRITE
      )
    ) {
      final ByteBuffer buffer = createBuffer(CONTENTS.getBytes().length);
      buffer.put(CONTENTS.getBytes());
      buffer.flip();

      while (buffer.hasRemaining()) {
        fileChannel.write(buffer);
      }
    } catch (IOException e) {
      throw new NIORuntimeException("Unable to write to file", e);
    }
  }

  private ByteBuffer createBuffer(final int length) {
    return ByteBuffer.allocate(length);
  }
}
