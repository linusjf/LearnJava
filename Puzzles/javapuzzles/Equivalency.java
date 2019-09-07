package javapuzzles;

@SuppressWarnings("PMD")
public enum Equivalency {
  ;
  private static final String HELLO = "Hello";

  public static void main(String... args) {
    String s1 = HELLO;
    String s2 = "Hello";
    String s3 = new String(HELLO);
    String s4 = new String(HELLO);
    System.err.println(s1 == s2);
    System.err.println(s1 == s3);
    System.err.println(s3 == s4);
  }
}
