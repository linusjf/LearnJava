package com.javacodegeeks.patterns.builderpattern;

/**
 * Describe interface <code>CarBuilder</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface CarBuilder {
  /**
   * Describe <code>buildBodyStyle</code> method here.
   *
   */
  void buildBodyStyle();

  /**
   * Describe <code>buildPower</code> method here.
   *
   */
  void buildPower();

  /**
   * Describe <code>buildEngine</code> method here.
   *
   */
  void buildEngine();

  /**
   * Describe <code>buildBrakes</code> method here.
   *
   */
  void buildBrakes();

  /**
   * Describe <code>buildSeats</code> method here.
   *
   */
  void buildSeats();

  /**
   * Describe <code>buildWindows</code> method here.
   *
   */
  void buildWindows();

  /**
   * Describe <code>buildFuelType</code> method here.
   *
   */
  void buildFuelType();

  /**
   * Describe <code>getCar</code> method here.
   *
   * @return a <code>Car</code> value
   */
  Car getCar();
}
