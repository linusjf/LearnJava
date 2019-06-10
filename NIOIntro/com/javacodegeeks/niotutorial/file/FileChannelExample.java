package com.javacodegeeks.niotutorial.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

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
   * @return <code>FileChannel</code> object based on fileOperation parameter
   * @throws FileNotFoundException if the file path does not point to a file.
   */
  default FileChannel createChannel(String path, FileOperation fileOperation)
      throws FileNotFoundException {
    final File file =
        new File(FileChannelReadExample.class.getClassLoader().getResource(path).getFile());
    return fileOperation == FileOperation.READ
        ? new FileInputStream(file).getChannel()
        : new FileOutputStream(file).getChannel();
  }
}
