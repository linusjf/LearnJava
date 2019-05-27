package com.javacodegeeks.patterns.builderpattern;

public class CarDirector {

  private CarBuilder carBuilder;

  public CarDirector(CarBuilder carBuilder) {
    this.carBuilder = carBuilder;
  }

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
