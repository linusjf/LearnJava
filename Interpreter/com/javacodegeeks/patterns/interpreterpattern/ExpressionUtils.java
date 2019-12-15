package com.javacodegeeks.patterns.interpreterpattern;

public final class ExpressionUtils {

  private ExpressionUtils() {
    throw new IllegalStateException("Private constructor");
  }

  public static boolean isOperator(String s) {
    return "+".equals(s) || "-".equals(s) || "*".equals(s);
  }

  @SuppressWarnings("checkstyle:returncount")
  public static Expression getOperator(
    String s,
    Expression left,
    Expression right
  ) {
    switch (s) {
      case "+":
        return new Addition(left, right);
      case "-":
        return new Subtraction(left, right);
      case "*":
        return new Product(left, right);
      default:
        return null;
    }
  }

  public static int interpret(
    String s,
    Expression leftExpression,
    Expression rightExpression
  ) {
    Expression expr = getOperator(s, leftExpression, rightExpression);
    return interpret(expr);
  }

  public static int interpret(Expression expr) {
    return expr.interpret();
  }
}
