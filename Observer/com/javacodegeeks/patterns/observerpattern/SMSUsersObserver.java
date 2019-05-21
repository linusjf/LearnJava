package com.javacodegeeks.patterns.observerpattern;

import java.util.Observable;

/**
 * Describe class <code>SMSUsersObserver</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class SMSUsersObserver implements java.util.Observer {

  private String desc;

  private final String userInfo;

  private final Observable observable;

  /**
   * Creates a new <code>SMSUsersObserver</code> instance.
   *
   * @param observable an <code>Observable</code> value
   * @param userInfo a <code>String</code> value
   */
  public SMSUsersObserver(Observable observable,String userInfo) {
    this.observable = observable;
    this.userInfo = userInfo;
  }

  /**
   * Describe <code>subscribe</code> method here.
   *
   */
  public void subscribe() {
    System.out.println("Subscribing " + userInfo + " to " 
        + ((CommentaryObjectObservable)observable).subjectDetails() + " ...");
    this.observable.addObserver(this);
    System.out.println("Subscribed successfully.");
  }

  /**
   * Describe <code>unSubscribe</code> method here.
   *
   */
  public void unSubscribe() {
    System.out.println("Unsubscribing " + userInfo + " to " 
        + ((CommentaryObjectObservable)observable).subjectDetails() + " ...");
    this.observable.deleteObserver(this);
    System.out.println("Unsubscribed successfully.");
  }

  @Override
  public void update(Observable o, Object arg) {
    desc = (String)arg;
    display();
  }

  private void display() {
    System.out.println("[" + userInfo + "]:" + desc);
  }
}
