package com.javacodegeeks.patterns.commandpattern;

import java.util.logging.Logger;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class EmailJob implements Job {
  private static final Logger LOGGER = Logger.getLogger(EmailJob.class.getName());
  private Email email;

  public void setEmail(Email email) {
    this.email = email;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void run() {
    LOGGER.info(() -> String.format("Job ID: %d executing email jobs.", Thread.currentThread().getId()));
    if (email != null) email.sendEmail();
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
    if (!(o instanceof EmailJob)) return false;
    EmailJob other = (EmailJob) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$email = this.email;
    Object other$email = other.email;
    if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof EmailJob;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $email = this.email;
    result = result * PRIME + ($email == null ? 43 : $email.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "EmailJob(email=" + this.email + ")";
  }
}
