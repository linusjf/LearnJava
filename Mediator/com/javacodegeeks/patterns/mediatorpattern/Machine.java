package com.javacodegeeks.patterns.mediatorpattern;

/**
 * Describe class <code>Machine</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Machine implements Colleague {
  private MachineMediator mediator;

  public Machine() {
    // empty constructor
  }

  public Machine(MachineMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void setMediator(MachineMediator mediator) {
    this.mediator = mediator;
  }

  /** Describe <code>start</code> method here. */
  public void start() {
    mediator.open();
  }

  /** Describe <code>wash</code> method here. */
  public void wash() {
    mediator.wash();
  }
}
