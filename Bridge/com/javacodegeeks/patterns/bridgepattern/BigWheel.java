package com.javacodegeeks.patterns.bridgepattern;

/**
 * Describe class <code>BigWheel</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class BigWheel extends Car {

  private final Product product;
  private final String carType;

  /**
   * Creates a new <code>BigWheel</code> instance.
   *
   * @param product a <code>Product</code> value
   * @param carType a <code>String</code> value
   */
  public BigWheel(Product product, String carType) {
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
