package asm;

@SuppressWarnings("PMD.SystemPrintln")
public final class MyClass {
  private MyClass() {
    throw new AssertionError("Private constructor");
  }

  @SuppressWarnings("checkstyle:MagicNumber")
  public static void main(String... args) {
    Integer myInt = Integer.valueOf(100);
    System.out.println(myInt);
  }
}
