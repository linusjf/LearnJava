package com.javacodegeeks.patterns.builderpattern;

/**
 * Describe class <code>Car</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings({ "PMD.ShortClassName", "PMD.DataClass" })
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
    sb.append("--------------")
      .append(carType)
      .append("--------------------- \\n Body: ")
      .append(bodyStyle)
      .append("\n Power: ")
      .append(power)
      .append("\n Engine: ")
      .append(engine)
      .append("\n Brakes: ")
      .append(brakes)
      .append("\n Seats: ")
      .append(seats)
      .append("\n Windows: ")
      .append(windows)
      .append("\n Fuel Type: ")
      .append(fuelType);
    return sb.toString();
  }
}
