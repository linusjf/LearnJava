package threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CommonForkJoinPoolThreads {
  ;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    final List<Integer> numbers = getNumbers();
    Thread t1 = new Thread(() -> numbers.parallelStream().forEach(n -> {
      try {
        Thread.sleep(5);
        System.out.printf("Loop %d : %s%n", n, Thread.currentThread());
      } catch (InterruptedException e) {
        System.out.println(e);
      }
    }));
    Thread t2 = new Thread(() -> numbers.parallelStream().forEach(n -> {
      try {
        Thread.sleep(5);
        System.out.printf("Loop %d : %s%n", n, Thread.currentThread());
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }));
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }

  private static List<Integer> getNumbers() {
    List<Integer> numbers = new ArrayList<>(5);
    for (int i = 0; i < 100; i++) numbers.add(i);
    return Collections.unmodifiableList(numbers);
  }
}
