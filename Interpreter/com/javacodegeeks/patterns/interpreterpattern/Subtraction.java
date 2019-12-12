package com.javacodegeeks.patterns.interpreterpattern;

public class Subtraction implements Expression {
  private final Expression leftExpression;
  private final Expression rightExpression;

  public Subtraction(Expression leftExpression, Expression rightExpression) {
    this.leftExpression = leftExpression;
    this.rightExpression = rightExpression;
  }

  @Override
  public int interpret() {
    return leftExpression.interpret() - rightExpression.interpret();
  }

  public Expression getLeftExpression() {
    return leftExpression;
  }

  public Expression getRightExpression() {
    return rightExpression;
  }

  @Override
  public String toString() {
    return leftExpression + " - " + rightExpression;
  }
}
