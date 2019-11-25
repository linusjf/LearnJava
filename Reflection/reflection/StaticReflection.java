package reflection;

public final class StaticReflection {

  private StaticReflection() {
    throw new IllegalStateException("Private constructor.");
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String... args) {
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
        System.err.println(roe);
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
      System.err.println(roe);
    }
  }

  // clang-format off
  static class StaticExample {
    int counter;
  }
  // clang-format on
}
