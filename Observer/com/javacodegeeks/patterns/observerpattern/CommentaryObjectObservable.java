package com.javacodegeeks.patterns.observerpattern;

import java.util.Observable;

/**
 * Describe class <code>CommentaryObjectObservable</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class CommentaryObjectObservable extends Observable implements Commentary {

  private String desc;// NOPMD

  private final String subjectDetails;

  /**
   * Creates a new <code>CommentaryObjectObservable</code> instance.
   *
   * @param subjectDetails a <code>String</code> value
   */
  public CommentaryObjectObservable(String subjectDetails) {
    this.subjectDetails = subjectDetails;
  }

  @Override
  public void setDesc(String desc) {
    this.desc = desc;
    setChanged();
    notifyObservers(desc);
  }

  /**
   * Describe <code>subjectDetails</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String subjectDetails() {
    return subjectDetails;
  }
}

