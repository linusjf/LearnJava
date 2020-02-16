// Generated from JSON2.g4 by ANTLR 4.8
package json2;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JSON2Parser}.
 */
public interface JSON2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JSON2Parser#json}.
	 * @param ctx the parse tree
	 */
	void enterJson(JSON2Parser.JsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSON2Parser#json}.
	 * @param ctx the parse tree
	 */
	void exitJson(JSON2Parser.JsonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnObject}
	 * labeled alternative in {@link JSON2Parser#object}.
	 * @param ctx the parse tree
	 */
	void enterAnObject(JSON2Parser.AnObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnObject}
	 * labeled alternative in {@link JSON2Parser#object}.
	 * @param ctx the parse tree
	 */
	void exitAnObject(JSON2Parser.AnObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyObject}
	 * labeled alternative in {@link JSON2Parser#object}.
	 * @param ctx the parse tree
	 */
	void enterEmptyObject(JSON2Parser.EmptyObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyObject}
	 * labeled alternative in {@link JSON2Parser#object}.
	 * @param ctx the parse tree
	 */
	void exitEmptyObject(JSON2Parser.EmptyObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayOfValues}
	 * labeled alternative in {@link JSON2Parser#array}.
	 * @param ctx the parse tree
	 */
	void enterArrayOfValues(JSON2Parser.ArrayOfValuesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayOfValues}
	 * labeled alternative in {@link JSON2Parser#array}.
	 * @param ctx the parse tree
	 */
	void exitArrayOfValues(JSON2Parser.ArrayOfValuesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyArray}
	 * labeled alternative in {@link JSON2Parser#array}.
	 * @param ctx the parse tree
	 */
	void enterEmptyArray(JSON2Parser.EmptyArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyArray}
	 * labeled alternative in {@link JSON2Parser#array}.
	 * @param ctx the parse tree
	 */
	void exitEmptyArray(JSON2Parser.EmptyArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSON2Parser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(JSON2Parser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSON2Parser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(JSON2Parser.PairContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link JSON2Parser#value}.
	 * @param ctx the parse tree
	 */
	void enterString(JSON2Parser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link JSON2Parser#value}.
	 * @param ctx the parse tree
	 */
	void exitString(JSON2Parser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link JSON2Parser#value}.
	 * @param ctx the parse tree
	 */
	void enterAtom(JSON2Parser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link JSON2Parser#value}.
	 * @param ctx the parse tree
	 */
	void exitAtom(JSON2Parser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ObjectValue}
	 * labeled alternative in {@link JSON2Parser#value}.
	 * @param ctx the parse tree
	 */
	void enterObjectValue(JSON2Parser.ObjectValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ObjectValue}
	 * labeled alternative in {@link JSON2Parser#value}.
	 * @param ctx the parse tree
	 */
	void exitObjectValue(JSON2Parser.ObjectValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayValue}
	 * labeled alternative in {@link JSON2Parser#value}.
	 * @param ctx the parse tree
	 */
	void enterArrayValue(JSON2Parser.ArrayValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayValue}
	 * labeled alternative in {@link JSON2Parser#value}.
	 * @param ctx the parse tree
	 */
	void exitArrayValue(JSON2Parser.ArrayValueContext ctx);
}