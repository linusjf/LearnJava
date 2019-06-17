package com.javacodegeeks.patterns.compositepattern;

/**
 * Describe class <code>HtmlElement</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class HtmlElement extends HtmlTag {
  private String tagName;
  private String startTag;
  private String endTag;
  private String tagBody;

  /**
   * Creates a new <code>HtmlElement</code> instance.
   *
   * @param tagName a <code>String</code> value
   */
  public HtmlElement(String tagName) {
    this.tagName = tagName;
    this.tagBody = "";
    this.startTag = "";
    this.endTag = "";
  }

  @Override
  public String getTagName() {
    return tagName;
  }

  @Override
  public void setStartTag(String tag) {
    this.startTag = tag;
  }

  @Override
  public void setEndTag(String tag) {
    this.endTag = tag;
  }

  @Override
  public void setTagBody(String tagBody) {
    this.tagBody = tagBody;
  }

  @Override
  public void generateHtml() {
    System.out.println(startTag + tagBody + endTag);
  }
}
