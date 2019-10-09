package com.javacodegeeks.patterns.factorymethodpattern;

/**
 * Describe class <code>OrderXMLDisplayService</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class OrderXMLDisplayService extends DisplayService {

  @Override
  public XMLParser getParser() {
    return new OrderXMLParser();
  }
}
