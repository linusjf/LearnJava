package com.javacodegeeks.patterns.factorymethodpattern;

/**
 * Describe class <code>ErrorXMLParser</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ErrorXMLParser implements XMLParser {

  @Override
  public String parse() {
    System.out.println("Parsing error XML...");
    return "Error XML Message";
  }
}
