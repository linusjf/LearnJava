package com.javacodegeeks.patterns.observerpattern;

import java.util.ArrayList;

/**
 * Describe class <code>TestObserver</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum TestObserver {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    final Subject subject =
        new CommentaryObject(new ArrayList<Observer>(), "Soccer Match [2014AUG24]");
    final Observer observer = new SMSUsers(subject, "Adam Warner [New York]");
    observer.subscribe();
    System.out.println();
    final Observer observer2 = new SMSUsers(subject, "Tim Ronney [London]");
    observer2.subscribe();
    final Commentary commentary = (Commentary) subject;
    commentary.setDesc("Welcome to live Soccer match");
    commentary.setDesc("Current score 0-0");
    System.out.println();
    observer2.unSubscribe();
    System.out.println();
    commentary.setDesc("It’s a goal!!");
    commentary.setDesc("Current score 1-0");
    System.out.println();
    final Observer observer3 = new SMSUsers(subject, "Marrie [Paris]");
    observer3.subscribe();
    System.out.println();
    commentary.setDesc("It’s another goal!!");
    commentary.setDesc("Half-time score 2-0");
  }
}
