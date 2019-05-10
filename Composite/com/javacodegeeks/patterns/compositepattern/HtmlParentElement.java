package com.javacodegeeks.patterns.compositepattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe class <code>HtmlParentElement</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class HtmlParentElement extends HtmlTag {
  private String tagName;
  private String startTag;
  private String endTag;
  private List<HtmlTag> childrenTag;

  /**
   * Creates a new <code>HtmlParentElement</code> instance.
   *
   * @param tagName a <code>String</code> value
   */
  public HtmlParentElement(String tagName) {
    this.tagName = tagName;
    this.startTag = "";
    this.endTag = "";
    this.childrenTag = new ArrayList<HtmlTag>();
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
  public void addChildTag(HtmlTag htmlTag) {
    childrenTag.add(htmlTag);
  }

  @Override
  public void removeChildTag(HtmlTag htmlTag) {
    childrenTag.remove(htmlTag);
  }

  @Override
  public List<HtmlTag> getChildren() {
    return childrenTag;
  }

  @Override
  public void generateHtml() {
    System.out.println(startTag);
    for (HtmlTag tag : childrenTag) {
      tag.generateHtml();
    }
    System.out.println(endTag);
  }
}
