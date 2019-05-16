package com.javacodegeeks.patterns.mediatorpattern;

/**
 * Describe interface <code>MachineMediator</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface MachineMediator {

  /**
   * Describe <code>start</code> method here.
   *
   */
  public void start();

  /**
   * Describe <code>wash</code> method here.
   *
   */
  public void wash();
  
  /**
   * Describe <code>open</code> method here.
   *
   */
  public void open();

  /**
   * Describe <code>closed</code> method here.
   *
   */
  public void closed();

  /**
   * Describe <code>on</code> method here.
   *
   */
  public void on();

  /**
   * Describe <code>off</code> method here.
   *
   */
  public void off();

  /**
   * Describe <code>checkTemperature</code> method here.
   *
   * @param temp an <code>int</code> value
   * @return a <code>boolean</code> value
   */
  public boolean checkTemperature(int temp);
}
