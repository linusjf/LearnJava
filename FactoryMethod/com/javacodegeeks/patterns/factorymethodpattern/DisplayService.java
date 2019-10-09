package com.javacodegeeks.patterns.factorymethodpattern;

/**
 * Describe class <code>DisplayService</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public abstract class DisplayService {

  /** Describe <code>display</code> method here. */
  public void display() {
    XMLParser parser = getParser();
    String msg = parser.parse();
    System.out.println(msg);
  }

  /**
   * Describe <code>getParser</code> method here.
   *
   * @return a <code>XMLParser</code> value
   */
  protected abstract XMLParser getParser();
}
