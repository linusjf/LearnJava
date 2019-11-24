package com.javacodegeeks.patterns.mementopattern;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class Memento {
  private final double xCoord;
  private final double yCoord;

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
