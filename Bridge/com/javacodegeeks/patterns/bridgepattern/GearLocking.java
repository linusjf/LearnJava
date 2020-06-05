package com.javacodegeeks.patterns.bridgepattern;

import java.util.Objects;

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
  public String toString() {
    return GearLocking.class + " : " +
      "Product name : " + prodName;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o instanceof GearLocking) {
     GearLocking gl = (GearLocking) o;
     return prodName.equals(gl.prodName);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return Objects.hashCode(prodName);
  }

  @Override
  public String productName() {
    return prodName;
  }

  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void produce() {
    System.out.println("Producing Gear Locking System");
  }
}
