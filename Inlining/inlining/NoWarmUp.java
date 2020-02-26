package inlining;

import java.util.logging.Logger;

public final class NoWarmUp {

  private static final Logger LOGGER =
      Logger.getLogger(NoWarmUp.class.getName());

  private NoWarmUp() {
    throw new IllegalStateException("Private constructor...");
  }

  public static void main(String[] args) {
    long start = System.nanoTime();
    ManualClassLoader.load();
    LOGGER.info(() -> "Total time taken : " + (System.nanoTime() - start));
  }
}
