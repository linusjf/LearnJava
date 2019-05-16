package com.javacodegeeks.patterns.observerpattern;

/**
 * Describe interface <code>Observer</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface Observer {

  /**
   * Describe <code>update</code> method here.
   *
   * @param desc a <code>String</code> value
   */
  public void update(String desc);
  
  /**
   * Describe <code>subscribe</code> method here.
   *
   */
  public void subscribe();
  
  /**
   * Describe <code>unSubscribe</code> method here.
   *
   */
  public void unSubscribe();
}
