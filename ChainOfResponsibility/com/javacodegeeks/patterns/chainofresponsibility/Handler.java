package com.javacodegeeks.patterns.chainofresponsibility;

/**
 * Describe interface <code>Handler</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface Handler {

  /**
   * Describe <code>setHandler</code> method here.
   *
   * @param handler a <code>Handler</code> value
   */
  void setHandler(Handler handler);

  /**
   * Describe <code>process</code> method here.
   *
   * @param file a <code>File</code> value
   */
  void process(File file);

  /**
   * Describe <code>getHandlerName</code> method here.
   *
   * @return a <code>String</code> value
   */
  String getHandlerName();
}
