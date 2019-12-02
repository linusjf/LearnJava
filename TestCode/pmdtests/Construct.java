package pmdtests;

import java.lang.reflect.Constructor;

public final class Construct {

  private Construct() {
    throw new IllegalStateException("Private constructor.");
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String... args) {
    // get all visible constructors
    Constructor<?>[] constructors = String.class.getConstructors();
  }
}
