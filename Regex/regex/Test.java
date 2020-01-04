package regex;

// A Simple Java program to demonstrate working of
// Pattern.matches() in Java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("PMD.ShortClassName")
public final class Test {
  private static final Pattern PASSWORD_PATTERN =
      Pattern.compile("^((\\$2(a){0,1}\\$){1})(.*)");

  private static final Pattern SEARCHFOR_PATTERN = Pattern.compile(
      "^(((Any)|(Network)|(Person)|(Host)|"
      + "(Domain)|(Organization)|(Group)|(Gateway)|(ASN)){1})$");

  private Test() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    String firstTest = "$2a$12$fghjhytygh";

    Matcher matcher = PASSWORD_PATTERN.matcher(firstTest);
    printResult(matcher);

    String secondTest = "$2$12$dfghhjjhggg";
    matcher = PASSWORD_PATTERN.matcher(secondTest);

    printResult(matcher);
    String thirdTest = "$2a$12$3PJIlsPnJBgPNr4qNcZPke";
    matcher = PASSWORD_PATTERN.matcher(thirdTest);

    printResult(matcher);
    testSearchForPattern();
  }

  private static void printResult(Matcher matcher) {
    System.out.println(matcher.matches());
    System.out.println(matcher.end(1));
  }

  private static void testSearchForPattern() {
    String firstTest = "all";

    Matcher matcher = SEARCHFOR_PATTERN.matcher(firstTest);

    printResult(matcher);

    String secondTest = "ASN";
    matcher = SEARCHFOR_PATTERN.matcher(secondTest);

    printResult(matcher);
    String thirdTest = "Irg";
    matcher = SEARCHFOR_PATTERN.matcher(thirdTest);

    printResult(matcher);
  }
}
