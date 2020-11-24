package jls;

@SuppressWarnings(
    {"PMD.UseEqualsToCompareStrings", "PMD.AvoidDuplicateLiterals"})
public final class TestStringInterned {
  private TestStringInterned() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] ignored) {
    String hello = "Hello";
    String lo = "lo";
    System.out.println(hello == "Hello");
    System.out.println(Other.hello == hello);
    System.out.println(jls.other.Other.HELLO == hello);
    System.out.println(hello
                       == "Hel"
                              + "lo");
    System.out.println(hello == "Hel" + lo);
    StringBuilder sb = new StringBuilder(5);
    sb.append("Hello");
    System.out.println(hello == sb.toString());
    System.out.println(hello == ("Hel" + lo).intern());
  }
}

class Other {
  static String hello = "Hello";
}
