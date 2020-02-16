// Generated from CSV2.g4 by ANTLR 4.8
package csv2;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CSV2Parser}.
 */
public interface CSV2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CSV2Parser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(CSV2Parser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSV2Parser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(CSV2Parser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSV2Parser#hdr}.
	 * @param ctx the parse tree
	 */
	void enterHdr(CSV2Parser.HdrContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSV2Parser#hdr}.
	 * @param ctx the parse tree
	 */
	void exitHdr(CSV2Parser.HdrContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSV2Parser#row}.
	 * @param ctx the parse tree
	 */
	void enterRow(CSV2Parser.RowContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSV2Parser#row}.
	 * @param ctx the parse tree
	 */
	void exitRow(CSV2Parser.RowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code text}
	 * labeled alternative in {@link CSV2Parser#field}.
	 * @param ctx the parse tree
	 */
	void enterText(CSV2Parser.TextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code text}
	 * labeled alternative in {@link CSV2Parser#field}.
	 * @param ctx the parse tree
	 */
	void exitText(CSV2Parser.TextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link CSV2Parser#field}.
	 * @param ctx the parse tree
	 */
	void enterString(CSV2Parser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CSV2Parser#field}.
	 * @param ctx the parse tree
	 */
	void exitString(CSV2Parser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code empty}
	 * labeled alternative in {@link CSV2Parser#field}.
	 * @param ctx the parse tree
	 */
	void enterEmpty(CSV2Parser.EmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code empty}
	 * labeled alternative in {@link CSV2Parser#field}.
	 * @param ctx the parse tree
	 */
	void exitEmpty(CSV2Parser.EmptyContext ctx);
}