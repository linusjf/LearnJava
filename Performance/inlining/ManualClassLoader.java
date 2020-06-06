package inlining;

public final class ManualClassLoader {
  private static final int LIMIT = 100_000;

  private ManualClassLoader() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  static void load() {
    for (int i = 0; i < LIMIT; i++) {
      Dummy dummy = new Dummy();
      dummy.emptyMethod();
    }
  }
}
