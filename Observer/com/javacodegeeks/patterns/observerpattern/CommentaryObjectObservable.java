package com.javacodegeeks.patterns.observerpattern;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Describe class <code>CommentaryObjectObservable</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class CommentaryObjectObservable implements Commentary {
  private String desc;  // NOPMD

  private final String subjectDetails;

  private PropertyChangeSupport pcs;

  /**
   * Creates a new <code>CommentaryObjectObservable</code> instance.
   *
   * @param subjectDetails a <code>String</code> value
   */
  public CommentaryObjectObservable(String subjectDetails) {
    this.subjectDetails = subjectDetails;
    this.pcs = new PropertyChangeSupport(this);
  }

  @Override
  public void setDesc(String desc) {
    final String oldValue = this.desc;
    this.desc = desc;
    pcs.firePropertyChange("desc", oldValue, desc);
  }

  /**
   * Describe <code>subjectDetails</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String subjectDetails() {
    return subjectDetails;
  }

  /**
   * Describe <code>addPropertyChangeListener</code> method here.
   *
   * @param listener a <code>PropertyChangeListener</code> value
   */
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.addPropertyChangeListener(listener);
  }

  /**
   * Describe <code>removePropertyChangeListener</code> method here.
   *
   * @param listener a <code>PropertyChangeListener</code> value
   */
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.removePropertyChangeListener(listener);
  }
}
