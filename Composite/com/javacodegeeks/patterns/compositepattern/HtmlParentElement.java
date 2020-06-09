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
  private final String tagName;
  private String startTag;
  private String endTag;
  private final List<HtmlTag> childrenTag;

  /**
   * Creates a new <code>HtmlParentElement</code> instance.
   *
   * @param tagName a <code>String</code> value
   */
  public HtmlParentElement(String tagName) {
    this.tagName = tagName;
    this.startTag = "";
    this.endTag = "";
    this.childrenTag = new ArrayList<>();
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
    throw new UnsupportedOperationException("Method setTagBody is not supported for class "
      + getClass());
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
  @SuppressWarnings("PMD.SystemPrintln")
  public void generateHtml() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    String ls = System.lineSeparator();
    StringBuilder sb = new StringBuilder();
    sb.append(startTag).append(ls);
    for (HtmlTag tag : childrenTag) sb.append(tag);
    sb.append(endTag).append(ls);
    return sb.toString();
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof HtmlParentElement)) return false;
    HtmlParentElement other = (HtmlParentElement) o;
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
    Object this$childrenTag = this.childrenTag;
    Object other$childrenTag = other.childrenTag;
    if (this$childrenTag == null ? other$childrenTag != null : !this$childrenTag.equals(other$childrenTag)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof HtmlParentElement;
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
    Object $childrenTag = this.childrenTag;
    result = result * PRIME + ($childrenTag == null ? 43 : $childrenTag.hashCode());
    return result;
  }
}
