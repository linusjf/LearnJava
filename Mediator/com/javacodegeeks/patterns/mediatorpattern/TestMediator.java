package com.javacodegeeks.patterns.mediatorpattern;

/**
 * Describe class <code>TestMediator</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum TestMediator {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {

    final Sensor sensor = new Sensor();
    final SoilRemoval soilRemoval = new SoilRemoval();
    final Motor motor = new Motor();
    final Machine machine = new Machine();
    final Heater heater = new Heater();
    final Valve valve = new Valve();
    final Button button = new Button();

    MachineMediator mediator = 
        new CottonMediator(machine, heater, motor, 
            sensor, soilRemoval, valve);
    button.setMediator(mediator);
    machine.setMediator(mediator);
    heater.setMediator(mediator);
    valve.setMediator(mediator);
    button.press();

    mediator = new DenimMediator(machine, heater, motor, sensor, soilRemoval, valve);
    button.setMediator(mediator);
    machine.setMediator(mediator);
    heater.setMediator(mediator);
    valve.setMediator(mediator);
    button.press();
  }
}
