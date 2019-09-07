package javapuzzles;

public enum Equivalency {
  ;

  public static void main(String... args) {
    String s1 = "Hello";
    String s2 = "Hello";
    String s3 = new String("Hello");
    String s4 = new String("Hello");
    System.err.println(s1==s2);
    System.err.println(s1==s3);
    System.err.println(s3==s4);
  }
}
