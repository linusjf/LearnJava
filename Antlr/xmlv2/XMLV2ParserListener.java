// Generated from XMLV2Parser.g4 by ANTLR 4.8
package xmlv2;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XMLV2Parser}.
 */
public interface XMLV2ParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XMLV2Parser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(XMLV2Parser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLV2Parser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(XMLV2Parser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLV2Parser#prolog}.
	 * @param ctx the parse tree
	 */
	void enterProlog(XMLV2Parser.PrologContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLV2Parser#prolog}.
	 * @param ctx the parse tree
	 */
	void exitProlog(XMLV2Parser.PrologContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLV2Parser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(XMLV2Parser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLV2Parser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(XMLV2Parser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLV2Parser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(XMLV2Parser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLV2Parser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(XMLV2Parser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLV2Parser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(XMLV2Parser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLV2Parser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(XMLV2Parser.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLV2Parser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(XMLV2Parser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLV2Parser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(XMLV2Parser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLV2Parser#chardata}.
	 * @param ctx the parse tree
	 */
	void enterChardata(XMLV2Parser.ChardataContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLV2Parser#chardata}.
	 * @param ctx the parse tree
	 */
	void exitChardata(XMLV2Parser.ChardataContext ctx);
	/**
	 * Enter a parse tree produced by {@link XMLV2Parser#misc}.
	 * @param ctx the parse tree
	 */
	void enterMisc(XMLV2Parser.MiscContext ctx);
	/**
	 * Exit a parse tree produced by {@link XMLV2Parser#misc}.
	 * @param ctx the parse tree
	 */
	void exitMisc(XMLV2Parser.MiscContext ctx);
}