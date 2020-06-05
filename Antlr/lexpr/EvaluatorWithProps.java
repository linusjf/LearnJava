package lexpr;

import java.util.Objects;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

/** Sample "calculator" using property of nodes. */
public class EvaluatorWithProps extends LExprBaseListener {
  /** maps nodes to integers with Map&lt;ParseTree,Integer&gt;. */
  ParseTreeProperty<Integer> values = new ParseTreeProperty<>();

  @Override
  public String toString() {
    return EvaluatorWithProps.class + " : " +
      (Object)this + " values = " +
      values;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o instanceof EvaluatorWithProps)
      return values.equals(((EvaluatorWithProps)o).values);
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(values);
  }
  
  // Need to pass e's value out of rule s : e ;
  @Override
  public void exitS(LExprParser.SContext ctx) {
    setValue(ctx, getValue(ctx.e()));
    // like: int s() { return e(); }
  }

  @Override
  public void exitMult(LExprParser.MultContext ctx) {
    int left = getValue(ctx.e(0));
    // e '*' e   # Mult
    int right = getValue(ctx.e(1));
    setValue(ctx, left * right);
  }

  @Override
  public void exitMod(LExprParser.ModContext ctx) {
    int left = getValue(ctx.e(0));
    // e '*' e   # Mult
    int right = getValue(ctx.e(1));
    setValue(ctx, left % right);
  }

  @Override
  public void exitAdd(LExprParser.AddContext ctx) {
    int left = getValue(ctx.e(0));
    // e '+' e   # Add
    int right = getValue(ctx.e(1));
    setValue(ctx, left + right);
  }

  @Override
  public void exitSub(LExprParser.SubContext ctx) {
    int left = getValue(ctx.e(0));
    // e '+' e   # Add
    int right = getValue(ctx.e(1));
    setValue(ctx, left - right);
  }

  @Override
  public void exitDiv(LExprParser.DivContext ctx) {
    int left = getValue(ctx.e(0));
    // e '+' e   # Add
    int right = getValue(ctx.e(1));
    setValue(ctx, left / right);
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void exitInt(LExprParser.IntContext ctx) {
    String intText = ctx.INT().getText();
    // INT   # Int
    setValue(ctx, Integer.parseInt(intText));
  }

  void setValue(ParseTree node, int value) {
    values.put(node, value);
  }

  int getValue(ParseTree node) {
    return values.get(node);
  }
}
