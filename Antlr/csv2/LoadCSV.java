package csv2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Excerpted from "The Definitive ANTLR 4 Reference", published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, courses, books,
 * articles, and the like. Contact us if you are in doubt. We make no guarantees that this code is
 * fit for any purpose. Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book
 * information. *
 */
public enum LoadCSV {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(LoadCSV.class.getName());

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    String inputFile = args.length > 0 ? args[0] : null;
    try {
      InputStream is = inputFile == null
                           ? System.in
                           : Files.newInputStream(Paths.get(inputFile));
      CharStream input = CharStreams.fromStream(is);
      CSV2Lexer lexer = new CSV2Lexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      CSV2Parser parser = new CSV2Parser(tokens);
      parser.setBuildParseTree(true);
      // tell ANTLR to build a parse tree
      ParseTree tree = parser.file();
      // parse
      // show tree in text form
      LOGGER.info(tree.toStringTree(parser));
      ParseTreeWalker walker = new ParseTreeWalker();
      Loader loader = new Loader();
      walker.walk(loader, tree);
      LOGGER.info(() -> loader.rows.toString());
    } catch (IOException ioe) {
      LOGGER.severe(ioe.getMessage());
    }
  }
}
