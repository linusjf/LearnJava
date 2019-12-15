package regex;

public enum SimpleRegexTest {
  ;
  public static void main(String[] args) {
    String sampleText = "this is the 1st test string";
    String sampleRegex = "\\d+\\w+";
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(sampleRegex);
    java.util.regex.Matcher m = p.matcher(sampleText);
    if (m.find()) {
      String matchedText = m.group();
      int matchedFrom = m.start();
      int matchedTo = m.end();
      System.out.println(
        "matched [" +
          matchedText +
          "] from " +
          matchedFrom +
          " to " +
          matchedTo +
          "."
      );
    } else {
      System.out.println("didn’t match");
    }
  }
}
