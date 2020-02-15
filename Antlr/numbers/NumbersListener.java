// Generated from Numbers.g4 by ANTLR 4.8
package numbers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NumbersParser}.
 */
public interface NumbersListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NumbersParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(NumbersParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumbersParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(NumbersParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumbersParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(NumbersParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumbersParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(NumbersParser.NumberContext ctx);
}