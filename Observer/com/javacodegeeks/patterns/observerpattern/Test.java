package com.javacodegeeks.patterns.observerpattern;

/**
 * Describe class <code>Test</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum Test {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    final CommentaryObjectObservable obj =
        new CommentaryObjectObservable("Soccer Match [2014AUG24]");
    final SMSUsersObserver observer =
        new SMSUsersObserver(obj, "Adam Warner [New York]");
    final SMSUsersObserver observer2 =
        new SMSUsersObserver(obj, "Tim Ronney [London]");
    observer.subscribe();
    observer2.subscribe();
    obj.setDesc("Welcome to live Soccer match");
    obj.setDesc("Current score 0-0");
    observer.unSubscribe();
    obj.setDesc("It’s a goal!!");
    obj.setDesc("Current score 1-0");
  }
}
