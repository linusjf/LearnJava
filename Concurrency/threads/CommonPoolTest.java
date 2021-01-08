package threads;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("PMD.SystemPrintln")
public enum CommonPoolTest {
  ;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    System.out.println("CPU Core: "
                       + Runtime.getRuntime().availableProcessors());
    System.out.println("CommonPool Parallelism: "
                       + ForkJoinPool.commonPool().getParallelism());
    System.out.println("CommonPool Common Parallelism: "
                       + ForkJoinPool.getCommonPoolParallelism());
    long start = System.nanoTime();
    List<CompletableFuture<Void>> futures =
        IntStream.range(0, 100)
            .mapToObj(i
                      -> CompletableFuture.runAsync(
                          CommonPoolTest::blockingOperation))
            .collect(Collectors.toUnmodifiableList());
    CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[ 0 ]))
        .join();
    System.out.println("Processed in "
                       + Duration.ofNanos(System.nanoTime() - start).toSeconds()
                       + " secs");
  }

  private static void blockingOperation() {
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
  }
}
