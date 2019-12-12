package com.javacodegeeks.patterns.interpreterpattern;

public class Number implements Expression {
  private final int num;

  public Number(int num) {
    this.num = num;
  }

  @Override
  public int interpret() {
    return getNum();
  }

  public int getNum() {
    return num;
  }

  @Override
  public String toString() {
    return Integer.toString(num);
  }
}
