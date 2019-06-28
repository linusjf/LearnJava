package com.javacodegeeks.patterns.mementopattern;

public class Memento {
  private double xCoord;
  private double yCoord;

  public Memento(double xCoord, double yCoord) {
    this.xCoord = xCoord;
    this.yCoord = yCoord;
  }

  public double getX() {
    return xCoord;
  }

  public double getY() {
    return yCoord;
  }
}
