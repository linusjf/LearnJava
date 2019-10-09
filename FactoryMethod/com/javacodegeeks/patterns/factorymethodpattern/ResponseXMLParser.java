package com.javacodegeeks.patterns.factorymethodpattern;

/**
 * Describe class <code>ResponseXMLParser</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ResponseXMLParser implements XMLParser {

  @Override
  public String parse() {
    System.out.println("Parsing response XML...");
    return "Response XML Message";
  }
}
