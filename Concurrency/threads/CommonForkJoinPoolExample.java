package threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public enum CommonForkJoinPoolExample {
  ;
  private static final int SIZE = 100;
  private static ScheduledExecutorService sec = Executors.newScheduledThreadPool(SIZE);

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      final List<Integer> numbers = getNumbers();
      numbers.parallelStream()
          .forEach(
              n ->
                  sec.schedule(
                      () -> System.out.printf("Loop %d : %s%n", n, Thread.currentThread()),
                      5,
                      TimeUnit.MILLISECONDS));
      sec.shutdown();
      sec.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException ie) {
      System.err.println(ie.getMessage());
    }
  }

  private static List<Integer> getNumbers() {
    List<Integer> numbers = new ArrayList<>(SIZE);
    for (int i = 0; i < SIZE; i++) numbers.add(i);
    return Collections.unmodifiableList(numbers);
  }
}
