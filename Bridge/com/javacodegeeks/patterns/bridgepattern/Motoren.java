package com.javacodegeeks.patterns.bridgepattern;

/**
 * Describe class <code>Motoren</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Motoren extends Car {
  private final Product product;
  private final String carType;

  /**
   * Creates a new <code>Motoren</code> instance.
   *
   * @param product a <code>Product</code> value
   * @param carType a <code>String</code> value
   */
  public Motoren(Product product, String carType) {
    super(product, carType);
    this.product = product;
    this.carType = carType;
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
    System.out.println("Modifing product " + product.productName() + " according to " + carType);
  }
}
