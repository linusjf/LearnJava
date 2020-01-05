package com.javacodegeeks.patterns.commandpattern;

import java.util.logging.Logger;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class FileIOJob implements Job {
  private static final Logger LOGGER =
      Logger.getLogger(FileIOJob.class.getName());

  private FileIO fileIO;

  public void setFileIO(FileIO fileIO) {
    this.fileIO = fileIO;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void run() {
    LOGGER.info(()
                    -> String.format("Job ID: %d executing fileIO jobs.",
                                     Thread.currentThread().getId()));
    if (fileIO != null)
      fileIO.execute();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
