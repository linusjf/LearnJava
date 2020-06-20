package threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public enum ForkJoinPoolExample {
  ;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    try {
      List<Integer> numbers = buildIntRange();
      ForkJoinPool forkJoinPool = new ForkJoinPool(4);
      Thread t1 =
          new Thread(
              () ->
                  forkJoinPool
                      .submit(
                          () -> {
                            numbers.parallelStream()
                                .forEach(
                                    n -> {
                                      try {
                                        TimeUnit.MILLISECONDS.sleep(5);
                                        System.out.println("Loop 1 : " + Thread.currentThread());
                                      } catch (InterruptedException e) {
                                        System.err.println(e);
                                      }
                                    });
                          })
                      .invoke());
      ForkJoinPool forkJoinPool2 = new ForkJoinPool(4);
      Thread t2 =
          new Thread(
              () ->
                  forkJoinPool2
                      .submit(
                          () -> {
                            numbers.parallelStream()
                                .forEach(
                                    n -> {
                                      try {
                                        TimeUnit.MILLISECONDS.sleep(5);
                                        System.out.println("Loop 2 : " + Thread.currentThread());
                                      } catch (InterruptedException e) {
                                        System.err.println(e);
                                      }
                                    });
                          })
                      .invoke());
      t1.start();
      t2.start();
      t1.join(5);
      t2.join(5);
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }

  private static List<Integer> buildIntRange() {
    List<Integer> numbers = new ArrayList<>(5);
    for (int i = 0; i < 100; i++) numbers.add(i);
    return Collections.unmodifiableList(numbers);
  }
}
