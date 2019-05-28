package com.javacodegeeks.patterns.mediatorpattern;

/**
 * Describe class <code>Valve</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Valve implements Colleague {
  private MachineMediator mediator;

  @Override
  public void setMediator(MachineMediator mediator) {
    this.mediator = mediator;
  }

  /** Describe <code>open</code> method here. */
  public void open() {
    System.out.println("Valve is opened...");
    System.out.println("Filling water...");
    mediator.closed();
  }

  /** Describe <code>closed</code> method here. */
  public void closed() {
    System.out.println("Valve is closed...");
    mediator.on();
  }
}
