package com.javacodegeeks.patterns.mediatorpattern;

/**
 * Describe class <code>Motor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Motor {
  /** Describe <code>startMotor</code> method here. */
  public void startMotor() {
    System.out.println("Start motor...");
  }

  /**
   * Describe <code>rotateDrum</code> method here.
   *
   * @param rpm an <code>int</code> value
   */
  public void rotateDrum(int rpm) {
    System.out.println("Rotating drum at " + rpm + " rpm.");
  }
}
