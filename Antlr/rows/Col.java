package rows;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Excerpted from "The Definitive ANTLR Reference", published by The Pragmatic Bookshelf. Copyrights
 * apply to this code. It may not be used to create training material, courses, books, articles, and
 * the like. Contact us if you are in doubt. We make no guarantees that this code is fit for any
 * purpose. Visit http://www.pragmaticprogrammer.com/titles/tpantlr for more book information.
 */
public enum Col {
  ;
  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis"})
  public static void main(String[] args) {
    try {
      // Create an input character stream from standard in
      CharStream input = CharStreams.fromStream(System.in, UTF_8);
      // Create an ExprLexer that feeds from that stream
      RowsLexer lexer = new RowsLexer(input);
      // Create a stream of tokens fed by the lexer
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      int col = Integer.parseInt(args[0]);
      RowsParser parser = new RowsParser(tokens, col);
      // pass column number!
      parser.setBuildParseTree(false);
      // don't waste time bulding a tree
      parser.file();
      // parse
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
