package com.javacodegeeks.patterns.mediatorpattern;

/**
 * Describe class <code>Button</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Button implements Colleague {
  private MachineMediator mediator;

  public Button() {
    // empty constructor
  }

  public Button(MachineMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void setMediator(MachineMediator mediator) {
    this.mediator = mediator;
  }

  /** Describe <code>press</code> method here. */
  public void press() {
    System.out.println("Button pressed.");
    mediator.start();
  }
}
