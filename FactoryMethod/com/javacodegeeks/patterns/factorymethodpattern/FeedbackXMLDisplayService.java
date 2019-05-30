package com.javacodegeeks.patterns.factorymethodpattern;

/**
 * Describe class <code>FeedbackXMLDisplayService</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class FeedbackXMLDisplayService extends DisplayService {
  @Override
  public XMLParser getParser() {
    return new FeedbackXML();
  }
}
