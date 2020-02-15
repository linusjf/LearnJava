package lexpr;
/**
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public enum TestLEvalVisitor {
  ;
  private static final Logger LOGGER =
    Logger.getLogger(TestLEvalVisitor.class.getName());

  public static void main(String[] args) {
        String inputFile =  args.length>0 ?
          inputFile = args[0]:
          null;
        try {
        InputStream is = inputFile == null ?
              System.in:
            Files.newInputStream(Paths.get(inputFile));
        CharStream input = CharStreams.fromStream(is);
        LExprLexer lexer = new LExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LExprParser parser = new LExprParser(tokens);
        parser.setBuildParseTree(true);      
        // tell ANTLR to build a parse tree
        ParseTree tree = parser.s(); 
        // parse
        // show tree in text form
        LOGGER.info(tree.toStringTree(parser));

        EvalVisitor evalVisitor = new EvalVisitor();
        int result = evalVisitor.visit(tree);
        LOGGER.info("visitor result = "+result);
        } catch (IOException ioe) {
          LOGGER.severe(ioe.getMessage());
        }
    }
}
