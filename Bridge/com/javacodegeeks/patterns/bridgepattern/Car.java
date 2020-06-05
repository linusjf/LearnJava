package com.javacodegeeks.patterns.bridgepattern;

import java.util.Objects;

/**
 * Describe class <code>Car</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings({"PMD.DataClass", "PMD.ShortClassName"})
public abstract class Car {
  protected final Product product;

  protected final String carType;

  /**
   * Creates a new <code>Car</code> instance.
   *
   * @param product a <code>Product</code> value
   * @param carType a <code>String</code> value
   */
  Car(Product product, String carType) {
    this.product = product;
    this.carType = carType;
  }

  @Override
  public String toString() {
    return getClass() + " : " +
      "Product : " + product +
      " Car type: " + carType;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o instanceof Car &&
        getClass().equals(o.getClass())) {
     Car bw = (Car) o;
     return product.equals(bw.product)
       && carType.equals(bw.carType);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(product, carType);
  }

  /** Describe <code>assemble</code> method here. */
  public abstract void assemble();

  /** Describe <code>produceProduct</code> method here. */
  public abstract void produceProduct();

  /** Describe <code>printDetails</code> method here. */
  @SuppressWarnings("PMD.SystemPrintln")
  public void printDetails() {
    System.out.println("Car: " + carType + ", Product:" + product.productName());
    System.out.println();
  }
}
