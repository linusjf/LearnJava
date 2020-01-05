package java6;

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
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * * Excerpted from "The Definitive ANTLR 4 Reference", published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, courses, books,
 * articles, and the like. Contact us if you are in doubt. We make no guarantees that this code is
 * fit for any purpose. Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book
 * information. *
 */
public enum InsertSerialID {
  ;
  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis"})
  public static void main(String[] args) {
    String inputFile = args.length > 0 ? args[0] : null;
    try (InputStream is = inputFile == null
                              ? System.in
                              : Files.newInputStream(Paths.get(inputFile));) {
      // Create an input character stream from standard in
      CharStream input = CharStreams.fromStream(is, UTF_8);
      // Create an ExprLexer that feeds from that stream
      Java6Lexer lexer = new Java6Lexer(input);
      // Create a stream of tokens fed by the lexer
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // Create a parser that feeds off the token stream
      Java6Parser parser = new Java6Parser(tokens);
      ParseTree tree = parser.compilationUnit();
      // parse
      ParseTreeWalker walker = new ParseTreeWalker();
      // create standard walker
      InsertSerialIDListener extractor = new InsertSerialIDListener(tokens);
      walker.walk(extractor, tree);
      // initiate walk of tree with listener
      // print back ALTERED stream
      System.out.println(extractor.rewriter.getText());
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
