package com.javacodegeeks.patterns.observerpattern;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;

/**
 * Describe class <code>SMSUsersObserver</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class SMSUsersObserver implements java.util.Observer, PropertyChangeListener {
  private String desc;

  private final String userInfo;

  private final CommentaryObjectObservable observable;

  /**
   * Creates a new <code>SMSUsersObserver</code> instance.
   *
   * @param observable an <code>Observable</code> value
   * @param userInfo a <code>String</code> value
   */
  public SMSUsersObserver(CommentaryObjectObservable observable, String userInfo) {
    this.observable = observable;
    this.userInfo = userInfo;
  }

  /** Describe <code>subscribe</code> method here. */
  public void subscribe() {
    System.out.println("Subscribing " + userInfo + " to " + observable.subjectDetails() + " ...");
    this.observable.addObserver(this);
    this.observable.addPropertyChangeListener(this);
    System.out.println("Subscribed successfully.");
  }

  /** Describe <code>unSubscribe</code> method here. */
  public void unSubscribe() {
    System.out.println("Unsubscribing " + userInfo + " to " + observable.subjectDetails() + " ...");
    this.observable.deleteObserver(this);
    this.observable.removePropertyChangeListener(this);
    System.out.println("Unsubscribed successfully.");
  }

  @Override
  public void update(Observable o, Object arg) {
    desc = (String) arg;
    display();
  }

  private void display() {
    System.out.println("[" + userInfo + "]:" + desc);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    desc = (String) evt.getNewValue();
    display();
  }
}
