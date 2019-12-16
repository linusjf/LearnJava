package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SimpleRegexTest {
  ;
  private static final String SAMPLE_TEXT = "this is the 1st test string";

  private static final String SAMPLE_REGEX = "\\d+\\w+";
  private static final Pattern P = Pattern.compile(SAMPLE_REGEX);

  private static final Matcher M = P.matcher(SAMPLE_TEXT);

  public static void main(String[] args) {
    if (M.find()) {
      String matchedText = M.group();
      int matchedFrom = M.start();
      int matchedTo = M.end();
      System.out.println(
          "matched [" + matchedText + "] from " + matchedFrom + " to " + matchedTo + ".");
    } else
      System.out.println("didn’t match");
  }
}
