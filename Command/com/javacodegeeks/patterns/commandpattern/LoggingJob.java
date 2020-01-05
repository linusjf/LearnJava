package com.javacodegeeks.patterns.commandpattern;

import java.util.logging.Logger;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class LoggingJob implements Job {
  private static final Logger LOGGER =
      Logger.getLogger(LoggingJob.class.getName());

  private Logging logging;

  public void setLogging(Logging logging) {
    this.logging = logging;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void run() {
    LOGGER.info(()
                    -> String.format("Job ID: %d executing logging jobs.",
                                     Thread.currentThread().getId()));
    if (logging != null)
      logging.log();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
