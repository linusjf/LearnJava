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
    LOGGER.info(() -> String.format("Job ID: %d executing sms jobs.", Thread.currentThread().getId()));
    if (sms != null) sms.sendSms();
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
    if (!(o instanceof SmsJob)) return false;
    SmsJob other = (SmsJob) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$sms = this.sms;
    Object other$sms = other.sms;
    if (this$sms == null ? other$sms != null : !this$sms.equals(other$sms)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof SmsJob;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $sms = this.sms;
    result = result * PRIME + ($sms == null ? 43 : $sms.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "SmsJob(sms=" + this.sms + ")";
  }
}
