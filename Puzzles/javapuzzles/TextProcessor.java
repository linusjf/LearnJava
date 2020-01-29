package javapuzzles;

public enum TextProcessor {
  ;

  public static void main(String[] args) {
    StringBuilder originalText = new StringBuilder("ABC");
    StringBuilder text = originalText;
    originalText.append("DEF");
    originalText = edit(text);
    originalText = text.append("XYZ");
    System.out.print(originalText);
  }

  static StringBuilder edit(StringBuilder originalText) {
    return originalText.delete(2, 3);
  }
}
