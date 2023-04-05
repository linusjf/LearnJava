package com.javacodegeeks.niotutorial.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Describe interface <code>FileChannelExample</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface FileChannelExample {
  enum FileOperation {
    READ,
    WRITE;
  }

  /**
   * Default method in interface to create channel.
   *
   * @param path file path
   * @param fileOperation File operation type as enum
   * @return <code>SeekableByteChannel</code> object based on fileOperation parameter
   * @throws FileNotFoundException if the file path does not point to a file.
   */
  @SuppressWarnings("PMD.LawOfDemeter")
  default SeekableByteChannel createChannel(String path,
                                            FileOperation fileOperation)
      throws FileNotFoundException, IOException {
    final File file = new File(Thread.currentThread()
                                   .getContextClassLoader()
                                   .getResource(path)
                                   .getFile());
    return fileOperation == FileOperation.READ
        ? Files.newByteChannel(Paths.get(file.getAbsolutePath()),
                               StandardOpenOption.READ)
        : Files.newByteChannel(Paths.get(file.getAbsolutePath()),
                               StandardOpenOption.CREATE,
                               StandardOpenOption.WRITE);
  }
}
