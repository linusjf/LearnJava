package com.javacodegeeks.patterns.compositepattern;

/**
 * Describe class <code>TestCompositePattern</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum TestCompositePattern {
  MAIN;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings("PMD.AvoidDuplicateLiterals")
  public static void main(String[] args) {
    HtmlTag parentTag = new HtmlParentElement("<html>");
    parentTag.setStartTag("<html>");
    parentTag.setEndTag("</html>");
    HtmlTag p1 = new HtmlParentElement("<body>");
    p1.setStartTag("<body>");
    p1.setEndTag("</body>");
    parentTag.addChildTag(p1);
    HtmlTag child1 = new HtmlElement("<P>");
    child1.setStartTag("<P>");
    child1.setEndTag("</P>");
    child1.setTagBody("Testing html tag library");
    p1.addChildTag(child1);
    child1 = new HtmlElement("<P>");
    child1.setStartTag("<P>");
    child1.setEndTag("</P>");
    child1.setTagBody("Paragraph 2");
    p1.addChildTag(child1);
    parentTag.generateHtml();
  }
}
