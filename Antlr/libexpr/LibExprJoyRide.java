package libexpr;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
public enum LibExprJoyRide {
  ;
  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    String inputFile = args.length > 0 ?
     args[0]: null; 
    try (InputStream is = inputFile == null
                              ? System.in
                              : Files.newInputStream(Paths.get(inputFile));) {
      // Create an input character stream from standard in
      CharStream input = CharStreams.fromStream(is, UTF_8);
      // Create an ExprLexer that feeds from that stream
      LibExprLexer lexer = new LibExprLexer(input);
      // Create a stream of tokens fed by the lexer
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // Create a parser that feeds off the token stream
      LibExprParser parser = new LibExprParser(tokens);
      ParseTree tree = parser.prog();
      // begin parsing at init rule
      System.out.println(tree.toStringTree(parser));
      // printLISP-styletree
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
