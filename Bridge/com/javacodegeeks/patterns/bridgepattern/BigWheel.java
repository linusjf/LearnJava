package com.javacodegeeks.patterns.bridgepattern;

import java.util.Objects;

/**
 * Describe class <code>BigWheel</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class BigWheel extends Car {

  /**
   * Creates a new <code>BigWheel</code> instance.
   *
   * @param product a <code>Product</code> value
   * @param carType a <code>String</code> value
   */
  public BigWheel(Product product, String carType) {
    super(product, carType);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o instanceof BigWheel &&
        getClass().equals(o.getClass())) {
     return super.equals(o);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), BigWheel.class);
  }

  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void assemble() {
    System.out.println("Assembling " + product.productName() + " for " + carType);
  }

  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void produceProduct() {
    product.produce();
    System.out.println("Modifying product " + product.productName() + " according to " + carType);
  }
}
