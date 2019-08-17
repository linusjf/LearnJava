package threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CommonForkJoinPoolExample {
  ;

  public static void main(String[] args) {
    final List<Integer> numbers = getNumbers();
    numbers.parallelStream().forEach(n -> {
      try {
        Thread.sleep(5);
        System.out.printf("Loop %d : %s\n", n, Thread.currentThread());
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    });
  }

  private static List<Integer> getNumbers() {
    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < 100; i++) numbers.add(i);
    return Collections.unmodifiableList(numbers);
  }
}
