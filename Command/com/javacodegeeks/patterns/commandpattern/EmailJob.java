package com.javacodegeeks.patterns.commandpattern;

import java.util.logging.Logger;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class EmailJob implements Job {
  private static final Logger LOGGER =
      Logger.getLogger(EmailJob.class.getName());

  private Email email;

  public void setEmail(Email email) {
    this.email = email;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void run() {
    LOGGER.info(()
                    -> String.format("Job ID: %d executing email jobs.",
                                     Thread.currentThread().getId()));

    if (email != null)
      email.sendEmail();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
