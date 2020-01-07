package com.javacodegeeks.patterns.mediatorpattern;

/**
 * Describe class <code>Sensor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Sensor {
  /**
   * Describe <code>checkTemperature</code> method here.
   *
   * @param temp an <code>int</code> value
   * @return a <code>boolean</code> value
   */
  @SuppressWarnings("PMD.SystemPrintln")
  public boolean checkTemperature(int temp) {
    System.out.println("Temperature reached " + temp + " *C");
    return true;
  }
}
