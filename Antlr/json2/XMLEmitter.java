package json2;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

@SuppressWarnings("PMD.TooManyMethods")
public class XMLEmitter extends JSON2BaseListener {
  ParseTreeProperty<String> xml = new ParseTreeProperty<>();

  String getXML(ParseTree ctx) {
    return xml.get(ctx);
  }

  void setXML(ParseTree ctx, String s) {
    xml.put(ctx, s);
  }

  @Override
  public void exitJson(JSON2Parser.JsonContext ctx) {
    setXML(ctx, getXML(ctx.getChild(0)));
  }

  @Override
  public void exitAnObject(JSON2Parser.AnObjectContext ctx) {
    StringBuilder buf = new StringBuilder();
    buf.append('\n');
    for (JSON2Parser.PairContext pctx: ctx.pair()) {
      buf.append(getXML(pctx));
    }
    setXML(ctx, buf.toString());
  }

  @Override
  public void exitEmptyObject(JSON2Parser.EmptyObjectContext ctx) {
    setXML(ctx, "");
  }

  @Override
  public void exitArrayOfValues(JSON2Parser.ArrayOfValuesContext ctx) {
    StringBuilder buf = new StringBuilder(40);
    buf.append('\n');
    for (JSON2Parser.ValueContext vctx: ctx.value()) {
      // conjure up element for valid XML
      buf.append("<element>").append(getXML(vctx)).append("</element>\n");
    }
    setXML(ctx, buf.toString());
  }

  @Override
  public void exitEmptyArray(JSON2Parser.EmptyArrayContext ctx) {
    setXML(ctx, "");
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void exitPair(JSON2Parser.PairContext ctx) {
    String tag = stripQuotes(ctx.STRING().getText());
    JSON2Parser.ValueContext vctx = ctx.value();
    String x = String.format("<%s>%s</%s>%n", tag, getXML(vctx), tag);
    setXML(ctx, x);
  }

  @Override
  public void exitObjectValue(JSON2Parser.ObjectValueContext ctx) {
    // analogous to String value() {return object();}
    setXML(ctx, getXML(ctx.object()));
  }

  @Override
  public void exitArrayValue(JSON2Parser.ArrayValueContext ctx) {
    // String value() {return array();}
    setXML(ctx, getXML(ctx.array()));
  }

  @Override
  public void exitAtom(JSON2Parser.AtomContext ctx) {
    setXML(ctx, ctx.getText());
  }

  @Override
  public void exitString(JSON2Parser.StringContext ctx) {
    setXML(ctx, stripQuotes(ctx.getText()));
  }

  static String stripQuotes(String s) {
    if (s == null || s.charAt(0) != '"')
      return s;
    return s.substring(1, s.length() - 1);
  }
}
