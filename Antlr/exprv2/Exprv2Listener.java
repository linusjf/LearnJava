// Generated from Exprv2.g4 by ANTLR 4.8
package exprv2;

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Exprv2Parser}.
 */
public interface Exprv2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Exprv2Parser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(Exprv2Parser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link Exprv2Parser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(Exprv2Parser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link Exprv2Parser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(Exprv2Parser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link Exprv2Parser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(Exprv2Parser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link Exprv2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Exprv2Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Exprv2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Exprv2Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Exprv2Parser#multExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(Exprv2Parser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Exprv2Parser#multExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(Exprv2Parser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Exprv2Parser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(Exprv2Parser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link Exprv2Parser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(Exprv2Parser.AtomContext ctx);
}