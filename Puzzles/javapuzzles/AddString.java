package javapuzzles;

@SuppressWarnings(
    {"PMD.AvoidUsingOctalValues", "checkstyle:magicnumber"})
public enum AddString {
  ;

  public static void main(String... args) {
    System.out.println((20 + 30) + "Eclipse");
    System.out.println("Eclipse" + 030 + 040);
  }
}
