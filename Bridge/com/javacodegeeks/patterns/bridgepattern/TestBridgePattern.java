package com.javacodegeeks.patterns.bridgepattern;

/**
 * Describe class <code>TestBridgePattern</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum TestBridgePattern {
  MAIN;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    final Product product = new CentralLocking("Central Locking System");
    final Product product2 = new GearLocking("Gear Locking System");
    Car car = new BigWheel(product, "BigWheel xz model");
    car.produceProduct();
    car.assemble();
    car.printDetails();
    car = new BigWheel(product2, "BigWheel xz model");
    car.produceProduct();
    car.assemble();
    car.printDetails();
    car = new Motoren(product, "Motoren lm model");
    car.produceProduct();
    car.assemble();
    car.printDetails();
    car = new Motoren(product2, "Motoren lm model");
    car.produceProduct();
    car.assemble();
    car.printDetails();
  }
}
