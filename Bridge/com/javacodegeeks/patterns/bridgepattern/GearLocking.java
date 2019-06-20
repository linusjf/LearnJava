package com.javacodegeeks.patterns.bridgepattern;

/**
 * Describe class <code>GearLocking</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class GearLocking implements Product {
  private final String prodName;

  /**
   * Creates a new <code>GearLocking</code> instance.
   *
   * @param productName a <code>String</code> value
   */
  public GearLocking(String productName) {
    this.prodName = productName;
  }

  @Override
  public String productName() {
    return prodName;
  }

  @Override
  public void produce() {
    System.out.println("Producing Gear Locking System");
  }
}
