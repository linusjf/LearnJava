package properties;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Logger;
import org.antlr.v4.misc.OrderedHashMap;
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
 * information.
 */
public enum TestPropertyFile {
  ;

  private static final Logger LOGGER =
    Logger.getLogger(TestPropertyFile.class.getName());

  public static class PropertyFileLoader extends PropertyFileBaseListener {
    Map<String, String> props = new OrderedHashMap<>();

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void exitProp(PropertyFileParser.PropContext ctx) {
      String id = ctx.ID().getText();
      // prop : ID '=' STRING '\n' ;
      String value = ctx.STRING().getText();
      props.put(id, value);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    String inputFile = args.length > 0 ? args[0]:null;
    try {
      InputStream is = inputFile == null ? 
        System.in:
        Files.newInputStream(Paths.get(inputFile));
      CharStream input = CharStreams.fromStream(is, StandardCharsets.UTF_8);
      PropertyFileLexer lexer = new PropertyFileLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      PropertyFileParser parser = new PropertyFileParser(tokens);
      ParseTree tree = parser.file();

      // create a standard ANTLR parse tree walker
      ParseTreeWalker walker = new ParseTreeWalker();
      // create listener then feed to walker
      PropertyFileLoader loader = new PropertyFileLoader();
      // walk parse tree
      walker.walk(loader, tree);
      // print results
      LOGGER.info(loader.props);
    } catch (IOException ioe) {
      LOGGER.severe(ioe.getMessage());
    }
  }
}
