package com.javacodegeeks.patterns.factorymethodpattern;

/**
 * Describe class <code>OrderXMLParser</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class OrderXMLParser implements XMLParser {

  @Override
  public String parse() {
    System.out.println("Parsing order XML...");
    return "Order XML Message";
  }
}
