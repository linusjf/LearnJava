package com.javacodegeeks.patterns.factorymethodpattern;

/**
 * Describe class <code>ResponseXMLDisplayService</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ResponseXMLDisplayService extends DisplayService {
  @Override
  public XMLParser getParser() {
    return new ResponseXMLParser();
  }
}
