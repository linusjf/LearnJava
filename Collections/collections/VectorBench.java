package collections;

import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

// Original source: https://www.javaspecialists.eu/archive/Issue280.html
@SuppressWarnings({"PMD.SystemPrintln",
                   "PMD.LawOfDemeter",
                   "PMD.UseArrayListInsteadOfVector"})
public enum VectorBench {
  ;

  private static final int RESULT = -389_705_856;
  private static int sum;

  public static void main(String... args) {
    for (int i = 0; i < 10; i++) {
      test(false);
      test(true);
    }
  }

  private static int computeSum() {

    if (sum == 0) {
      sum = 1023 * 1024 / 2 * (100_000_000 / 1024);
      int mod = (100_000_000 & 1023) - 1;
      sum += (mod * ++mod) / 2;
    }
    return sum;
  }

  private static void test(boolean parallel) {
    long time = 0L;
    try {
      IntStream range = IntStream.range(1, 100_000_000);
      if (parallel)
        range = range.parallel();

      ThreadLocal<List<Integer>> lists = ThreadLocal.withInitial(() -> {
        long time2 = System.nanoTime();
        List<Integer> result = new Vector<>();
        for (int i = 0; i < 1024; i++)
          result.add(i);
        time2 = System.nanoTime() - time2;
        System.out.printf("Thread Local Storage: %dms%n", time2 / 1_000_000);
        return result;
      });
      time = System.nanoTime();
      System.out.println("Formulaic sum = " + computeSum());
      time = System.nanoTime() - time;
      System.out.printf("Formulaic time: %.6fms%n",
                        (double)time / (double)1_000_000);

      time = System.nanoTime();
      System.out.println("Result sum = " + RESULT);
      time = System.nanoTime() - time;
      System.out.printf("Result time : %.6fms%n",
                        (double)time / (double)1_000_000);

      time = System.nanoTime();
      System.out.println("Sum = "
                         + range.map(i -> lists.get().get(i & 1023)).sum());
    } finally {
      time = System.nanoTime() - time;
      System.out.printf(
          "%s %dms%n", parallel ? "parallel" : "sequential", time / 1_000_000);
    }
  }
}
