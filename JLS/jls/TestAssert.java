package jls;

public final class TestAssert {
  @SuppressWarnings("all")
  private TestAssert() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String[] ignored) {
    Baz.testAsserts();
    // Will execute after Baz is initialized.
  }
}

class Bar {
  static {
    Baz.testAsserts();
    // Will execute before Baz is initialized!
  }
}

class Baz extends Bar {
  @SuppressWarnings("PMD.UnusedAssignment")
  static void testAsserts() {
    boolean enabled = false;
    assert enabled = true;
    System.out.println("Asserts " + (enabled ? "enabled" : "disabled"));
  }
}
