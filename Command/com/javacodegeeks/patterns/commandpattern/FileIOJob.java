package com.javacodegeeks.patterns.commandpattern;

import java.util.logging.Logger;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class FileIOJob implements Job {
  private static final Logger LOGGER = Logger.getLogger(FileIOJob.class.getName());
  private FileIO fileIO;

  public void setFileIO(FileIO fileIO) {
    this.fileIO = fileIO;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void run() {
    LOGGER.info(() -> String.format("Job ID: %d executing fileIO jobs.", Thread.currentThread().getId()));
    if (fileIO != null) fileIO.execute();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof FileIOJob)) return false;
    FileIOJob other = (FileIOJob) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$fileIO = this.fileIO;
    Object other$fileIO = other.fileIO;
    if (this$fileIO == null ? other$fileIO != null : !this$fileIO.equals(other$fileIO)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof FileIOJob;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $fileIO = this.fileIO;
    result = result * PRIME + ($fileIO == null ? 43 : $fileIO.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "FileIOJob(fileIO=" + this.fileIO + ")";
  }
}
