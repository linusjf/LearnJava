package com.javacodegeeks.patterns.strategypattern;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class TextEditor {
  private final TextFormatter textFormatter;

  public TextEditor(TextFormatter textFormatter) {
    this.textFormatter = textFormatter;
  }

  public void publishText(String text) {
    textFormatter.format(text);
  }

  @Override
  public String toString() {
    return "TextEditor(textFormatter=" + this.textFormatter + ")";
  }

  @SuppressWarnings("all")
  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof TextEditor))
      return false;
    TextEditor other = (TextEditor)o;
    if (!other.canEqual((Object)this))
      return false;
    Object this$textFormatter = this.textFormatter;
    Object other$textFormatter = other.textFormatter;
    if (this$textFormatter == null
            ? other$textFormatter != null
            : !this$textFormatter.equals(other$textFormatter))
      return false;
    return true;
  }

  protected boolean canEqual(Object other) {
    return other instanceof TextEditor;
  }

  @SuppressWarnings("all")
  @Override
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $textFormatter = this.textFormatter;
    result = result * PRIME
             + ($textFormatter == null ? 43 : $textFormatter.hashCode());
    return result;
  }
}
