package reflection;

import java.util.logging.Logger;

public final class StaticReflection {
  private static final Logger LOGGER =
      Logger.getLogger(StaticReflection.class.getName());

  private StaticReflection() {
    throw new IllegalStateException("Private constructor.");
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis",
                     "PMD.LawOfDemeter",
                     "PMD.SystemPrintln"})
  public static void
  main(String... args) {
    try {
      // 1 access static class
      System.out.println("directly " + StaticExample.class.getName());

      // 2 using for name directly throws an exception
      Class<?> forname;
      System.out.println(
          "Class.forName reflection.StaticReflection.StaticExample");
      try {
        forname = Class.forName("reflection.StaticReflection.StaticExample");
      } catch (ReflectiveOperationException roe) {
        LOGGER.severe(roe.getMessage());
      }
      System.out.println("using $ " + StaticExample.class.getName());

      // 3 using $ would work but is not that nice
      forname = Class.forName("reflection.StaticReflection$StaticExample");
      System.out.println(forname);

      // 4 another way iterating through all classes declared inside this class
      Class<?>[] classes = StaticReflection.class.getDeclaredClasses();
      for (Class<?> class1: classes)
        System.out.println("iterating through declared classes "
                           + class1.getName());
    } catch (ReflectiveOperationException roe) {
      LOGGER.severe(roe.getMessage());
    }
  }

  // clang-format off
  static class StaticExample {
    int counter;
  }
  // clang-format on
}
