package com.javacodegeeks.patterns.builderpattern;

/**
 * Describe class <code>CarDirector</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class CarDirector {
  private final CarBuilder carBuilder;

  /**
   * Creates a new <code>CarDirector</code> instance.
   *
   * @param carBuilder a <code>CarBuilder</code> value
   */
  public CarDirector(CarBuilder carBuilder) {
    this.carBuilder = carBuilder;
  }

  /** Describe <code>build</code> method here. */
  public void build() {
    carBuilder.buildBodyStyle();
    carBuilder.buildPower();
    carBuilder.buildEngine();
    carBuilder.buildBrakes();
    carBuilder.buildSeats();
    carBuilder.buildWindows();
    carBuilder.buildFuelType();
  }
}
