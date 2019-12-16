package com.javacodegeeks.patterns.interpreterpattern;

import java.util.Stack;

public enum TestInterpreterPattern {
  ;

  public static void main(String[] args) {
    String tokenString = "7 3 - 2 1 + *";
    Expression expr = parseTokenString(tokenString);
    System.out.println("( " + tokenString + " ): " + getResult(expr));
  }

  private static int getResult(Expression expr) {
    return expr.interpret();
  }

  private static Expression parseTokenString(String tokenString) {
    Stack<Expression> stack = new Stack<>();
    String[] tokenArray = tokenString.split(" ");
    for (String s : tokenArray) {
      if (ExpressionUtils.isOperator(s)) {
        Expression rightExpression = stack.pop();
        Expression leftExpression = stack.pop();
        int result = ExpressionUtils.interpret(s, leftExpression, rightExpression);
        stack.push(new Number(result));
      } else {
        Expression i = new Number(Integer.parseInt(s));
        stack.push(i);
      }
    }
    return stack.pop();
  }
}
