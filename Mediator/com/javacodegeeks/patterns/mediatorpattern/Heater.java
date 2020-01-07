package com.javacodegeeks.patterns.mediatorpattern;

/**
 * Describe class <code>Heater</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Heater implements Colleague {
  private MachineMediator mediator;

  public Heater() {
    // empty constructor
  }

  public Heater(MachineMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void setMediator(MachineMediator mediator) {
    this.mediator = mediator;
  }

  /**
   * Describe <code>on</code> method here.
   *
   * @param temp an <code>int</code> value
   */
  @SuppressWarnings({"PMD.ShortMethodName", "PMD.SystemPrintln"})
  public void on(int temp) {
    System.out.println("Heater is on...");
    if (mediator.checkTemperature(temp)) {
      System.out.println("Temperature is set to " + temp);
      mediator.off();
    }
  }

  /** Describe <code>off</code> method here. */
  @SuppressWarnings("PMD.SystemPrintln")
  public void off() {
    System.out.println("Heater is off...");
    mediator.wash();
  }
}
