package com.javacodegeeks.patterns.compositepattern;

import java.util.List;

/**
 * Describe class <code>HtmlTag</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public abstract class HtmlTag {
  private static final String UNSUPPORTED_OPERATION =
      "Current operation is not support for this object.";

  /**
   * Describe <code>getTagName</code> method here.
   *
   * @return a <code>String</code> value
   */
  public abstract String getTagName();

  /**
   * Describe <code>setStartTag</code> method here.
   *
   * @param tag a <code>String</code> value
   */
  public abstract void setStartTag(String tag);

  /**
   * Describe <code>setEndTag</code> method here.
   *
   * @param tag a <code>String</code> value
   */
  public abstract void setEndTag(String tag);

  /**
   * Describe <code>setTagBody</code> method here.
   *
   * @param tagBody a <code>String</code> value
   */
  public abstract void setTagBody(String tagBody);

  /**
   * Describe <code>addChildTag</code> method here.
   *
   * @param htmlTag a <code>HtmlTag</code> value
   */
  public abstract void addChildTag(HtmlTag htmlTag);

  /**
   * Describe <code>removeChildTag</code> method here.
   *
   * @param htmlTag a <code>HtmlTag</code> value
   */
  public abstract void removeChildTag(HtmlTag htmlTag);

  /**
   * Describe <code>getChildren</code> method here.
   *
   * @return a <code>List</code> object
   */
  public abstract List<HtmlTag> getChildren();

  /** Describe <code>generateHtml</code> method here. */
  public abstract void generateHtml();
}
