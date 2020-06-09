package com.javacodegeeks.patterns.commandpattern;

import java.util.logging.Logger;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class LoggingJob implements Job {
  private static final Logger LOGGER = Logger.getLogger(LoggingJob.class.getName());
  private Logging logging;

  public void setLogging(Logging logging) {
    this.logging = logging;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void run() {
    LOGGER.info(() -> String.format("Job ID: %d executing logging jobs.", Thread.currentThread().getId()));
    if (logging != null) logging.log();
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
    if (!(o instanceof LoggingJob)) return false;
    LoggingJob other = (LoggingJob) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$logging = this.logging;
    Object other$logging = other.logging;
    if (this$logging == null ? other$logging != null : !this$logging.equals(other$logging)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof LoggingJob;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $logging = this.logging;
    result = result * PRIME + ($logging == null ? 43 : $logging.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "LoggingJob(logging=" + this.logging + ")";
  }
}
