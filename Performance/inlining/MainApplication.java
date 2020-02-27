package inlining;

import java.util.logging.Logger;

public final class MainApplication {

  private static final Logger LOGGER =
      Logger.getLogger(MainApplication.class.getName());

  private MainApplication() {
    throw new IllegalStateException("Private constructor...");
  }

  static {
    long start = System.nanoTime();
    ManualClassLoader.load();
    long end = System.nanoTime();
    LOGGER.info(() -> "Warm Up time : " + (end - start));
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    long start = System.nanoTime();
    ManualClassLoader.load();
    LOGGER.info(() -> "Total time taken : " + (System.nanoTime() - start));
  }
}
