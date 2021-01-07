package graph;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public enum GraphDSLAntlrSample {
  ;
  private static final Charset UTF_8 = StandardCharsets.UTF_8;
  private static final Logger LOGGER =
      Logger.getLogger(GraphDSLAntlrSample.class.getName());

  @SuppressWarnings({"PMD.SystemPrintln", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    try (
        // Reading the DSL script
        InputStream is = ClassLoader.getSystemResourceAsStream(args[0])) {

      System.out.println("Loaded grammar");
      // Loading the DSL script into the ANTLR stream.
      CharStream cs = CharStreams.fromStream(is, UTF_8);

      System.out.println("created charstream");
      // Passing the input to the lexer to create tokens
      GraphLexer lexer = new GraphLexer(cs);
      System.out.println("created lexer");

      CommonTokenStream tokens = new CommonTokenStream(lexer);
      System.out.println("created tokens");

      // Passing the tokens to the parser to create the parse trea.
      GraphParser parser = new GraphParser(tokens);
      System.out.println("created parser");

      // Semantic model to be populated
      Graph g = new Graph();
      System.out.println("created graph");

      // Adding the listener to facilitate walking through parse tree.
      parser.addParseListener(new MyGraphListenerImpl(g));

      System.out.println("added listener");
      // invoking the parser.
      parser.graph();

      System.out.println("walked graph");
      Graph.printGraph(g);
      System.out.println("printed graph");
    } catch (IOException ioe) {
      LOGGER.severe(ioe.getMessage());
    }
  }
}
