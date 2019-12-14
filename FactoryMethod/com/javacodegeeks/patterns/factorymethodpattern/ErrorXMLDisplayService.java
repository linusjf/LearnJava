package com.javacodegeeks.patterns.factorymethodpattern;

/**
 * Describe class <code>ErrorXMLDisplayService</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ErrorXMLDisplayService extends DisplayService {
  @Override
  public XMLParser getParser() {
    return new ErrorXMLParser();
  }
}
