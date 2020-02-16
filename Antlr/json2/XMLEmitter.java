package json2;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class XMLEmitter extends JSON2BaseListener {
  ParseTreeProperty<String> xml = new ParseTreeProperty<String>();

  String getXML(ParseTree ctx) {
    return xml.get(ctx);
  }

  void setXML(ParseTree ctx, String s) {
    xml.put(ctx, s);
  }

  public void exitJson(JSON2Parser.JsonContext ctx) {
    setXML(ctx, getXML(ctx.getChild(0)));
  }

  public void exitAnObject(JSON2Parser.AnObjectContext ctx) {
    StringBuilder buf = new StringBuilder();
    buf.append("\n");
    for (JSON2Parser.PairContext pctx: ctx.pair()) {
      buf.append(getXML(pctx));
    }
    setXML(ctx, buf.toString());
  }

  public void exitEmptyObject(JSON2Parser.EmptyObjectContext ctx) {
    setXML(ctx, "");
  }

  public void exitArrayOfValues(JSON2Parser.ArrayOfValuesContext ctx) {
    StringBuilder buf = new StringBuilder();
    buf.append("\n");
    for (JSON2Parser.ValueContext vctx: ctx.value()) {
      buf.append("<element>");
      // conjure up element for valid XML
      buf.append(getXML(vctx));
      buf.append("</element>");
      buf.append("\n");
    }
    setXML(ctx, buf.toString());
  }

  public void exitEmptyArray(JSON2Parser.EmptyArrayContext ctx) {
    setXML(ctx, "");
  }

  public void exitPair(JSON2Parser.PairContext ctx) {
    String tag = stripQuotes(ctx.STRING().getText());
    JSON2Parser.ValueContext vctx = ctx.value();
    String x = String.format("<%s>%s</%s>\n", tag, getXML(vctx), tag);
    setXML(ctx, x);
  }

  public void exitObjectValue(JSON2Parser.ObjectValueContext ctx) {
    // analogous to String value() {return object();}
    setXML(ctx, getXML(ctx.object()));
  }

  public void exitArrayValue(JSON2Parser.ArrayValueContext ctx) {
    setXML(ctx, getXML(ctx.array()));
    // String value() {return array();}
  }

  public void exitAtom(JSON2Parser.AtomContext ctx) {
    setXML(ctx, ctx.getText());
  }

  public void exitString(JSON2Parser.StringContext ctx) {
    setXML(ctx, stripQuotes(ctx.getText()));
  }

  public static String stripQuotes(String s) {
    if (s == null || s.charAt(0) != '"')
      return s;
    return s.substring(1, s.length() - 1);
  }
}
