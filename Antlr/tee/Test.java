package tee;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public enum Test {
  ;
  private static final Logger LOGGER = Logger.getLogger(Test.class.getName());

  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  public static void main(String[] args) {
    try {
      // create a CharStream that reads from standard input
      CharStream input = CharStreams.fromStream(System.in, UTF_8);
      // create a lexer that feeds off of input CharStream
      TeeLexer lexer = new TeeLexer(input);
      // create a buffer of tokens pulled from the lexer
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer
      TeeParser parser = new TeeParser(tokens);
      // begin parsing at rule r
      parser.r();
    } catch (IOException e) {
      LOGGER.severe(e.getMessage());
    }
  }
}
