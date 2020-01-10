package cymbol;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Excerpted from "The Definitive ANTLR Reference", published by The Pragmatic Bookshelf. Copyrights
 * apply to this code. It may not be used to create training material, courses, books, articles, and
 * the like. Contact us if you are in doubt. We make no guarantees that this code is fit for any
 * purpose. Visit http://www.pragmaticprogrammer.com/titles/tpantlr for more book information.
 */
public enum DisplayParseTree {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(DisplayParseTree.class.getName());
  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  @SuppressWarnings({"PMD.LawOfDemeter",
                     "PMD.DataflowAnomalyAnalysis",
                     "PMD.SystemPrintln"})
  public static void
  main(String[] args) {
    try {
      // Create an input character stream from standard in
      CharStream input = CharStreams.fromStream(System.in, UTF_8);
      // Create an ExprLexer that feeds from that stream
      CymbolLexer lexer = new CymbolLexer(input);
      // Create a stream of tokens fed by the lexer
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      CymbolParser parser = new CymbolParser(tokens);
      ParseTree tree = parser.file();
      // parse
      System.out.println(tree.toStringTree(parser));
    } catch (IOException ioe) {
      LOGGER.severe(ioe.getMessage());
    }
  }
}
