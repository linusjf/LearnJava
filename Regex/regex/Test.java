package regex;

// A Simple Java program to demonstrate working of
// Pattern.matches() in Java

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Test {
  private static final Pattern passwordPattern =
      Pattern.compile("^((\\$2(a){0,1}\\$){1})(.*)");

  private Test() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    String firstTest = "$2a$12$fghjhytygh";

    String secondTest = "$2$12$dfghhjjhggg";

    String thirdTest = "$2a$12$3PJIlsPnJBgPNr4qNcZPke";

    Matcher matcher = passwordPattern.matcher(firstTest);

    System.out.println(matcher.matches());
    System.out.println(matcher.end(1));

    matcher = passwordPattern.matcher(secondTest);

    System.out.println(matcher.matches());
      System.out.println(matcher.end(1));
    matcher = passwordPattern.matcher(thirdTest);

    System.out.println(matcher.matches());
      System.out.println(matcher.end(1));
  }
}
