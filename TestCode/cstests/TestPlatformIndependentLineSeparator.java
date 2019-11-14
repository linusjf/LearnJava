package cstests;

/**
 * * This class lists the varied combinations where the line separator denoted by \n is to be
 * replaced by either a call to System.lineSeparator(), %n in a System.out.printf() or
 * System.getProperty("line.separator").
 *
 * <p>The same rule should apply to \r\n as well, if specified. \n and \r\n must not be flagged in
 * this comment block. *
 */
public class TestPlatformIndependentLineSeparator {
  /**
   * * This class lists the varied combinations where the line separator denoted by \n is to be
   * replaced by either a call to System.lineSeparator(), %n in a System.out.printf() or
   * System.getProperty("line.separator").
   *
   * <p>The same rule should apply to \r\n as well, if specified. \n and \r\n must not be flagged in
   * this comment block either. *
   */
  // test if \n is flagged here. It shouldn't.
  // test if \r\n is flagged here. It shouldn't.
  private static final String testString = "\nTest to catch line separator\n";

  private static final String testString2 = "\r\nTest to catch line separator\r\n";

  private static final String testString3 = "\\nTest to escape line separator\\n";

  private static final String testString4 = "\\r\\nTest to escape line separator\\r\\n";

  // this must be flagged
  private static final String CRLF = "/r/n";

  // this must be flagged
  private static final String LF = "/n";

  static {
    System.setProperty("line.separator", "/r/n");

    // above must not be flagged
    System.out.println(testString);
    System.out.println(testString2);
    System.out.println(testString3);
    System.out.println(testString4);
    StringBuilder sb = new StringBuilder();
    sb.append('\n'); // this should be flagged
    sb.append("\r\n"); // this should be flagged
    System.out.println(sb.toString());
    System.out.printf("%s\n", sb.toString());

    // the above line must be flagged
    char[] chars = new char[] {'\n', '\r', 'a', 'b', 'c'};

    // the above shouldn't be flagged. Maybe, it // should or someone could do
    // the following
    String charString = new String(chars);
    System.out.print(charString);

    StringBuffer sb1 = new StringBuffer();
    sb1.append('\n'); // this should be flagged
    sb1.append("\r\n"); // this should be flagged
    System.out.println(sb1.toString());
    System.out.printf("%s\n", sb1.toString());

    // the above line must be flagged
    System.setProperty("line.separator", LF);
  }

  public static void main(String... args) {
    System.setProperty("line.separator", "/r/n");

    // above must not be flagged
    System.out.println(testString);
    System.out.println(testString2);
    System.out.println(testString3);
    System.out.println(testString4);
    StringBuilder sb = new StringBuilder();
    sb.append('\n'); // this should be flagged
    sb.append("\r\n"); // this should be flagged
    System.out.println(sb.toString());
    System.out.printf("%s\n", sb.toString());

    // the above line must be flagged
    char[] chars = new char[] {'\n', '\r', 'a', 'b', 'c'};

    // the above shouldn't be flagged. Maybe, it // should or someone could do
    // the following
    String charString = new String(chars);
    System.out.print(charString);

    StringBuffer sb1 = new StringBuffer();
    sb1.append('\n'); // this should be flagged
    sb1.append("\r\n"); // this should be flagged
    System.out.println(sb1.toString());
    System.out.printf("%s\n", sb1.toString());

    // the above line must be flagged
    // This line is not to be flagged
    System.out.printf("%s%n", sb1.toString());
    System.setProperty("line.separator", LF);
  }
}
