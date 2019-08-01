package threads;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public enum ForkJoinPuzzle {
  ;
  private static int counter = 0;

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
    parallelStream().forEach(val -> process());
    System.out.println("counter = " + counter);
  }

  private static void process() {
    try {
      String processor = Thread.currentThread().getName();
      System.out.println("Processing: " + processor);
      Runnable updateTask = () -> parallelStream().forEach(value -> {
        System.out.println("Updating: " + Thread.currentThread().getName()
                           + " value = " + value + " "
                           + ForkJoinPool.commonPool());
        counter++;
      });
      Thread thread = new Thread(updateTask, "Worker for " + processor);
      thread.start();
      System.out.println("Waiting: " + processor);
      thread.join();
    } catch (InterruptedException e) {
      System.err.println(e);
      Thread.currentThread().interrupt();
    }
  }

  private static IntStream parallelStream() {
    return IntStream.range(0, Runtime.getRuntime().availableProcessors())
        .parallel();
  }
}
