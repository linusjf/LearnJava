package jls;

@SuppressWarnings("PMD")
public final strictfp class TestStrictFP {
  private TestStrictFP() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String... args) {
    double d = 8.0E307;
    System.out.println(4.0f * d * 0.5f);
    System.out.println(2.0f * d);
  }
}
