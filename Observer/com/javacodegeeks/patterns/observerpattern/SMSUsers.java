package com.javacodegeeks.patterns.observerpattern;

/**
 * Describe class <code>SMSUsers</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class SMSUsers implements Observer {
  private final Subject subject;

  private String desc;

  private String userInfo;

  /**
   * Creates a new <code>SMSUsers</code> instance.
   *
   * @param subject a <code>Subject</code> value
   * @param userInfo a <code>String</code> value
   */
  public SMSUsers(Subject subject, String userInfo) {
    if (subject == null) {
      throw new IllegalArgumentException("No Publisher found.");
    }
    this.subject = subject;
    this.userInfo = userInfo;
  }

  @Override
  public void update(String desc) {
    this.desc = desc;
    display();
  }

  private void display() {
    System.out.println("[" + userInfo + "]: " + desc);
  }

  @Override
  public void subscribe() {
    System.out.println("Subscribing " + userInfo + " to "
                       + subject.subjectDetails() + " ...");
    this.subject.subscribeObserver(this);
    System.out.println("Subscribed successfully.");
  }

  @Override
  public void unSubscribe() {
    System.out.println("Unsubscribing " + userInfo + " to "
                       + subject.subjectDetails() + " ...");
    this.subject.unSubscribeObserver(this);
    System.out.println("Unsubscribed successfully.");
  }
}
