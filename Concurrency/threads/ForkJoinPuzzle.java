package threads;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import java.util.concurrent.atomic.AtomicInteger;

public enum ForkJoinPuzzle {
  ;
  private static AtomicInteger counter =
    new AtomicInteger();
  private static Map<String, Integer> processorsCount =
      new ConcurrentHashMap<>();

  public static void main(String... args) {

    Random random = new Random();

    int parallelism =
        random.nextInt(Runtime.getRuntime().availableProcessors() + 1);
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
                       String.valueOf(parallelism));

    System.out.printf("Set parallelism to %d\n", parallelism);
    System.out.println("Forkjoin pool size: "
                       + ForkJoinPool.getCommonPoolParallelism());
    System.out.println("No. of processors: "
                       + Runtime.getRuntime().availableProcessors());
    // sequentialStream().forEach(val -> process());
    parallelStream().forEach(val -> process());
    System.out.println("counter = " + counter.get());
    // printProcessorCount();
  }

  private static void printProcessorCount() {
    Set<Map.Entry<String, Integer>> mapEntries = processorsCount.entrySet();
    synchronized (System.out) {
      System.out.println("***********''***********************'");
      for (Map.Entry<String, Integer> entry: mapEntries)
        System.out.println(entry.getKey() + " : " + entry.getValue());
      System.out.println("***********''***********************'");
    }
  }

  private static void process() {
    try {
      String processor = Thread.currentThread().getName();
      System.out.println("Processing: " + processor);
      Runnable updateTask = () -> parallelStream().forEach(value -> {
      System.out.printf("Active thread count: %d\n",
          Thread.activeCount());
        System.out.println("Updating: " + Thread.currentThread().getName()
                           + " value = " + value + " "
                           + ForkJoinPool.commonPool());
        counter.incrementAndGet();
        System.out.printf("Thread %s\n",Thread.currentThread());
      });
      Thread thread = new Thread(updateTask, "Worker for " + processor);
      thread.start();
      System.out.println("Waiting: " + processor);
      Integer count = processorsCount.get(processor);
      if (count == null)
        processorsCount.put(processor, 1);
      else
        processorsCount.put(processor, ++count);
      thread.join();
      System.out.println("Ended: " + processor);
      printProcessorCount();
    } catch (InterruptedException e) {
      System.err.println(e);
      Thread.currentThread().interrupt();
    }
  }

  private static IntStream parallelStream() {
    return IntStream.range(0, Runtime.getRuntime().availableProcessors())
        .parallel();
  }

  private static IntStream sequentialStream() {
    return IntStream.range(0, Runtime.getRuntime().availableProcessors())
        .sequential();
  }
}
