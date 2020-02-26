package inlining;

public class ManualClassLoader {

  private ManualClassLoader() {
    throw new IllegalStateException("Private constructor.");
  }

  private static final int LIMIT = 100_000;

  protected static void load() {
    for (int i = 0; i < LIMIT; i++) {
      Dummy dummy = new Dummy();
      dummy.mEmpty();
    }
  }
}
