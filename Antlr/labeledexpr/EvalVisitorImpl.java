package labeledexpr;

import java.util.HashMap;
import java.util.Map;

/**
 * * Excerpted from "The Definitive ANTLR 4 Reference", published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, courses, books,
 * articles, and the like. Contact us if you are in doubt. We make no guarantees that this code is
 * fit for any purpose. Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book
 * information. *
 */
public class EvalVisitorImpl extends LabeledExprBaseVisitor<Integer> {
  /** "memory" for our calculator; variable/value pairs go here. */
  Map<String, Integer> memory = new HashMap<>();

  @Override
  public String toString() {
    return EvalVisitorImpl.class + " : " +
      (Object)this + "memory : " +
      memory;
  }
  
  /** ID '=' expr NEWLINE. */
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
    String id = ctx.ID().getText();
    // id is left-hand side of '='
    int value = visit(ctx.expr());
    // compute value of expression on right
    memory.put(id, value);
    // store it in our memory
    return value;
  }

  /** expr NEWLINE. */
  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
    Integer value = visit(ctx.expr());
    // evaluate the expr child
    System.out.println(value);
    // print the result
    return 0;
    // return dummy value
  }

  /** INT. */
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public Integer visitInt(LabeledExprParser.IntContext ctx) {
    return Integer.valueOf(ctx.INT().getText());
  }

  /** ID. */
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public Integer visitId(LabeledExprParser.IdContext ctx) {
    String id = ctx.ID().getText();
    Integer val = memory.get(id);
    if (val == null)
      return 0;
    return val;
  }

  /** expr op=('*'|'/') expr. */
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
    int left = visit(ctx.expr(0));
    // get value of left subexpression
    int right = visit(ctx.expr(1));
    // get value of right subexpression
    if (ctx.op.getType() == LabeledExprParser.MUL)
      return left * right;
    return left / right;
    // must be DIV
  }

  /** expr op=('+'|'-') expr. */
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
    int left = visit(ctx.expr(0));
    // get value of left subexpression
    int right = visit(ctx.expr(1));
    // get value of right subexpression
    if (ctx.op.getType() == LabeledExprParser.ADD)
      return left + right;
    return left - right;
    // must be SUB
  }

  /** '(' expr ')'. */
  @Override
  public Integer visitParens(LabeledExprParser.ParensContext ctx) {
    return visit(ctx.expr());
    // return child expr's value
  }
}
