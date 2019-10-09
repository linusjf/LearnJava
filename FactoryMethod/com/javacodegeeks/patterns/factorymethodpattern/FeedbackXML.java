package com.javacodegeeks.patterns.factorymethodpattern;

/**
 * Describe class <code>FeedbackXML</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class FeedbackXML implements XMLParser {

  @Override
  public String parse() {
    System.out.println("Parsing feedback XML...");
    return "Feedback XML Message";
  }
}
