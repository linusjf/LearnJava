package com.javacodegeeks.patterns.compositepattern;

import java.util.List;

/**
 * Describe class <code>HtmlElement</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.DataClass")
public class HtmlElement extends HtmlTag {
  private final String tagName;
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
  @SuppressWarnings("PMD.SystemPrintln")
  public void generateHtml() {
    System.out.println(this);
  }

  @Override
  public void addChildTag(HtmlTag htmlTag) {
    throw new UnsupportedOperationException("Method addChildTag is not supported for class "
      + getClass());
  }
  
  @Override
  public List<HtmlTag> getChildren() {
    throw new UnsupportedOperationException("Method getChildren is not supported for class "
      + getClass());
  }

  @Override
  public void removeChildTag(HtmlTag htmlTag) {
    throw new UnsupportedOperationException("Method removeChildTag is not supported for class "
      + getClass());
  }
  @Override
  public String toString() {
    return startTag + tagBody + endTag;
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof HtmlElement)) return false;
    HtmlElement other = (HtmlElement) o;
    if (!other.canEqual((Object) this)) return false;
    if (!super.equals(o)) return false;
    Object this$tagName = this.getTagName();
    Object other$tagName = other.getTagName();
    if (this$tagName == null ? other$tagName != null : !this$tagName.equals(other$tagName)) return false;
    Object this$startTag = this.startTag;
    Object other$startTag = other.startTag;
    if (this$startTag == null ? other$startTag != null : !this$startTag.equals(other$startTag)) return false;
    Object this$endTag = this.endTag;
    Object other$endTag = other.endTag;
    if (this$endTag == null ? other$endTag != null : !this$endTag.equals(other$endTag)) return false;
    Object this$tagBody = this.tagBody;
    Object other$tagBody = other.tagBody;
    if (this$tagBody == null ? other$tagBody != null : !this$tagBody.equals(other$tagBody)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof HtmlElement;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = super.hashCode();
    Object $tagName = this.getTagName();
    result = result * PRIME + ($tagName == null ? 43 : $tagName.hashCode());
    Object $startTag = this.startTag;
    result = result * PRIME + ($startTag == null ? 43 : $startTag.hashCode());
    Object $endTag = this.endTag;
    result = result * PRIME + ($endTag == null ? 43 : $endTag.hashCode());
    Object $tagBody = this.tagBody;
    result = result * PRIME + ($tagBody == null ? 43 : $tagBody.hashCode());
    return result;
  }
}
