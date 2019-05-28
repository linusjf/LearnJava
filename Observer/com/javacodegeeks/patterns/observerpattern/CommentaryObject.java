package com.javacodegeeks.patterns.observerpattern;

import java.util.List;

/**
 * Describe class <code>CommentaryObject</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class CommentaryObject implements Subject, Commentary {
  private final List<Observer> observers;

  private String desc;

  private final String subjectDetails;

  /**
   * Creates a new <code>CommentaryObject</code> instance.
   *
   * @param subjectDetails a <code>String</code> value
   */
  public CommentaryObject(List<Observer> observers, String subjectDetails) {
    this.observers = observers;

    this.subjectDetails = subjectDetails;
  }

  @Override
  public void subscribeObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void unSubscribeObserver(Observer observer) {
    final int index = observers.indexOf(observer);
    observers.remove(index);
  }

  @Override
  public void notifyObservers() {
    System.out.println();
    for (Observer observer : observers) {
      observer.update(desc);
    }
  }

  @Override
  public void setDesc(String desc) {
    this.desc = desc;
    notifyObservers();
  }

  @Override
  public String subjectDetails() {
    return subjectDetails;
  }
}
