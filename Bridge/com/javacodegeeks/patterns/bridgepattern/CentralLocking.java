package com.javacodegeeks.patterns.bridgepattern;

import java.util.Objects;

/**
 * Describe class <code>CentralLocking</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class CentralLocking implements Product {
  private final String prodName;

  /**
   * Creates a new <code>CentralLocking</code> instance.
   *
   * @param productName a <code>String</code> value
   */
  public CentralLocking(String productName) {
    this.prodName = productName;
  }

  @Override
  public String toString() {
    return getClass() + " : "
        + "Product Name : " + prodName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o instanceof CentralLocking && getClass().equals(o.getClass())) {
      CentralLocking bw = (CentralLocking)o;
      return prodName.equals(bw.prodName);
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
    System.out.println("Producing Central Locking System");
  }
}
