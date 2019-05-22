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
  void update(String desc);
  
  /**
   * Describe <code>subscribe</code> method here.
   *
   */
  void subscribe();
  
  /**
   * Describe <code>unSubscribe</code> method here.
   *
   */
  void unSubscribe();
}
