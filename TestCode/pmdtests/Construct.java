package pmdtests;

import java.lang.reflect.Constructor;

@SuppressWarnings("PMD.LawOfDemeter")
public final class Construct {

  private Construct() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    // get all visible constructors
    Constructor<?>[] constructors = String.class.getConstructors();
  }
}
