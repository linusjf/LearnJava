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

  /**
   * Describe <code>build</code> method here.
   */
  public void build() {
    carBuilder.buildBodyStyle();
    carBuilder.buildPower();
    carBuilder.buildEngine();
    carBuilder.buildBrakes();
    carBuilder.buildSeats();
    carBuilder.buildWindows();
    carBuilder.buildFuelType();
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "CarDirector(carBuilder=" + this.carBuilder + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof CarDirector)) return false;
    CarDirector other = (CarDirector) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$carBuilder = this.carBuilder;
    Object other$carBuilder = other.carBuilder;
    if (this$carBuilder == null ? other$carBuilder != null : !this$carBuilder.equals(other$carBuilder)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof CarDirector;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $carBuilder = this.carBuilder;
    result = result * PRIME + ($carBuilder == null ? 43 : $carBuilder.hashCode());
    return result;
  }
}
