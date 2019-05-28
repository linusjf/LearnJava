package com.javacodegeeks.patterns.observerpattern;

/**
 * Describe interface <code>Subject</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface Subject {
  /**
   * Describe <code>subscribeObserver</code> method here.
   *
   * @param observer an <code>Observer</code> value
   */
  void subscribeObserver(Observer observer);

  /**
   * Describe <code>unSubscribeObserver</code> method here.
   *
   * @param observer an <code>Observer</code> value
   */
  void unSubscribeObserver(Observer observer);

  /** Describe <code>notifyObservers</code> method here. */
  void notifyObservers();

  /**
   * Describe <code>subjectDetails</code> method here.
   *
   * @return a <code>String</code> value
   */
  String subjectDetails();
}
