package com.javacodegeeks.patterns.commandpattern;

import java.util.logging.Logger;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class SmsJob implements Job {

  private static final Logger LOGGER = Logger.getLogger(SmsJob.class.getName());

  private Sms sms;

  public void setSms(Sms sms) {
    this.sms = sms;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void run() {
    LOGGER.info(()
                    -> String.format("Job ID: %d executing sms jobs.",
                                     Thread.currentThread().getId()));
    if (sms != null)
      sms.sendSms();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
