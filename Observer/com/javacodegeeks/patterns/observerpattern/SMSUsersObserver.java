package com.javacodegeeks.patterns.observerpattern;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Describe class <code>SMSUsersObserver</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class SMSUsersObserver implements PropertyChangeListener {
  private String desc;

  private final String userInfo;

  private final CommentaryObjectObservable observable;

  /**
   * Creates a new <code>SMSUsersObserver</code> instance.
   *
   * @param observable an <code>Observable</code> value
   * @param userInfo a <code>String</code> value
   */
  public SMSUsersObserver(CommentaryObjectObservable observable,
                          String userInfo) {
    this.observable = observable;
    this.userInfo = userInfo;
  }

  /** Describe <code>subscribe</code> method here. */
  public void subscribe() {
    System.out.println("Subscribing " + userInfo + " to "
                       + observable.getSubjectDetails() + " ...");
    this.observable.addPropertyChangeListener(this);
    System.out.println("Subscribed successfully.");
  }

  /** Describe <code>unSubscribe</code> method here. */
  public void unSubscribe() {
    System.out.println("Unsubscribing " + userInfo + " to "
                       + observable.getSubjectDetails() + " ...");
    this.observable.removePropertyChangeListener(this);
    System.out.println("Unsubscribed successfully.");
  }

  private void display() {
    System.out.println("[" + userInfo + "]:" + desc);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    desc = (String)evt.getNewValue();
    display();
  }
}
