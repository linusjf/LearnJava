package com.javacodegeeks.patterns.bridgepattern;

/**
 * Describe class <code>Car</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings({"PMD.DataClass", "PMD.ShortClassName"})
public abstract class Car {
  private final Product product;

  private final String carType;

  /**
   * Creates a new <code>Car</code> instance.
   *
   * @param product a <code>Product</code> value
   * @param carType a <code>String</code> value
   */
  public Car(Product product, String carType) {
    this.product = product;
    this.carType = carType;
  }

  /** Describe <code>assemble</code> method here. */
  public abstract void assemble();

  /** Describe <code>produceProduct</code> method here. */
  public abstract void produceProduct();

  /** Describe <code>printDetails</code> method here. */
  @SuppressWarnings("PMD.SystemPrintln")
  public void printDetails() {
    System.out.println("Car: " + carType
                       + ", Product:" + product.productName());
    System.out.println();
  }
}
