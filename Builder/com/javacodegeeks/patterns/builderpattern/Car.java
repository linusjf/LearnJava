package com.javacodegeeks.patterns.builderpattern;

/**
 * Describe class <code>Car</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings({"PMD.ShortClassName", "PMD.DataClass"})
public class Car {
  private String bodyStyle;
  private String power;
  private String engine;
  private String brakes;
  private String seats;
  private String windows;
  private String fuelType;
  private final String carType;

  /**
   * Creates a new <code>Car</code> instance.
   *
   * @param carType a <code>String</code> value
   */
  public Car(String carType) {
    this.carType = carType;
  }

  /**
   * Describe <code>getBodyStyle</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getBodyStyle() {
    return bodyStyle;
  }

  /**
   * Describe <code>setBodyStyle</code> method here.
   *
   * @param bodyStyle a <code>String</code> value
   */
  public void setBodyStyle(String bodyStyle) {
    this.bodyStyle = bodyStyle;
  }

  /**
   * Describe <code>getPower</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getPower() {
    return power;
  }

  /**
   * Describe <code>setPower</code> method here.
   *
   * @param power a <code>String</code> value
   */
  public void setPower(String power) {
    this.power = power;
  }

  /**
   * Describe <code>getEngine</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getEngine() {
    return engine;
  }

  /**
   * Describe <code>setEngine</code> method here.
   *
   * @param engine a <code>String</code> value
   */
  public void setEngine(String engine) {
    this.engine = engine;
  }

  /**
   * Describe <code>getBrakes</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getBrakes() {
    return brakes;
  }

  /**
   * Describe <code>setBrakes</code> method here.
   *
   * @param brakes a <code>String</code> value
   */
  public void setBrakes(String brakes) {
    this.brakes = brakes;
  }

  /**
   * Describe <code>getSeats</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getSeats() {
    return seats;
  }

  /**
   * Describe <code>setSeats</code> method here.
   *
   * @param seats a <code>String</code> value
   */
  public void setSeats(String seats) {
    this.seats = seats;
  }

  /**
   * Describe <code>getWindows</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getWindows() {
    return windows;
  }

  /**
   * Describe <code>setWindows</code> method here.
   *
   * @param windows a <code>String</code> value
   */
  public void setWindows(String windows) {
    this.windows = windows;
  }

  /**
   * Describe <code>getFuelType</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getFuelType() {
    return fuelType;
  }

  /**
   * Describe <code>setFuelType</code> method here.
   *
   * @param fuelType a <code>String</code> value
   */
  public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(150);
    sb.append("--------------").append(carType).append("--------------------- \\n Body: ").append(bodyStyle).append("\n Power: ").append(power).append("\n Engine: ").append(engine).append("\n Brakes: ").append(brakes).append("\n Seats: ").append(seats).append("\n Windows: ").append(windows).append("\n Fuel Type: ").append(fuelType);
    return sb.toString();
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Car)) return false;
    Car other = (Car) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$bodyStyle = this.getBodyStyle();
    Object other$bodyStyle = other.getBodyStyle();
    if (this$bodyStyle == null ? other$bodyStyle != null : !this$bodyStyle.equals(other$bodyStyle)) return false;
    Object this$power = this.getPower();
    Object other$power = other.getPower();
    if (this$power == null ? other$power != null : !this$power.equals(other$power)) return false;
    Object this$engine = this.getEngine();
    Object other$engine = other.getEngine();
    if (this$engine == null ? other$engine != null : !this$engine.equals(other$engine)) return false;
    Object this$brakes = this.getBrakes();
    Object other$brakes = other.getBrakes();
    if (this$brakes == null ? other$brakes != null : !this$brakes.equals(other$brakes)) return false;
    Object this$seats = this.getSeats();
    Object other$seats = other.getSeats();
    if (this$seats == null ? other$seats != null : !this$seats.equals(other$seats)) return false;
    Object this$windows = this.getWindows();
    Object other$windows = other.getWindows();
    if (this$windows == null ? other$windows != null : !this$windows.equals(other$windows)) return false;
    Object this$fuelType = this.getFuelType();
    Object other$fuelType = other.getFuelType();
    if (this$fuelType == null ? other$fuelType != null : !this$fuelType.equals(other$fuelType)) return false;
    Object this$carType = this.carType;
    Object other$carType = other.carType;
    if (this$carType == null ? other$carType != null : !this$carType.equals(other$carType)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Car;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $bodyStyle = this.getBodyStyle();
    result = result * PRIME + ($bodyStyle == null ? 43 : $bodyStyle.hashCode());
    Object $power = this.getPower();
    result = result * PRIME + ($power == null ? 43 : $power.hashCode());
    Object $engine = this.getEngine();
    result = result * PRIME + ($engine == null ? 43 : $engine.hashCode());
    Object $brakes = this.getBrakes();
    result = result * PRIME + ($brakes == null ? 43 : $brakes.hashCode());
    Object $seats = this.getSeats();
    result = result * PRIME + ($seats == null ? 43 : $seats.hashCode());
    Object $windows = this.getWindows();
    result = result * PRIME + ($windows == null ? 43 : $windows.hashCode());
    Object $fuelType = this.getFuelType();
    result = result * PRIME + ($fuelType == null ? 43 : $fuelType.hashCode());
    Object $carType = this.carType;
    result = result * PRIME + ($carType == null ? 43 : $carType.hashCode());
    return result;
  }
}
