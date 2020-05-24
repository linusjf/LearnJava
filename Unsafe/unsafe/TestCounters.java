package unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public enum TestCounters {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(TestCounters.class.getName());

  private static final int NUM_OF_THREADS = 1000;

  private static final long NUM_OF_INCREMENTS = 10_000;

  private static boolean areAllTasksComplete(List<Future<?>> futures) {
    for (Future<?> ft: futures) {
      if (!ft.isDone())
        return false;
    }
    return true;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static long executeCounterClient(Counter counter)
      throws InterruptedException {
    ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
    // creating instance of specific counter
    final long before = System.currentTimeMillis();
    List<Future<?>> futures = new ArrayList<>();
    for (int i = 0; i < NUM_OF_THREADS; i++) 
      futures.add(service.submit(new CounterClient(counter, NUM_OF_INCREMENTS)));
    service.shutdown();
    service.awaitTermination(1, TimeUnit.MINUTES);
    long after = System.currentTimeMillis();
    LOGGER.info(() -> counter.getClass().getName());
    LOGGER.info(() -> "All tasks completed: " + areAllTasksComplete(futures));
    LOGGER.info(() -> "Counter result: " + counter.get());
    long timePassed = after - before;
    LOGGER.info(() -> "Time passed in ms:" + timePassed);
    return timePassed;
  }

  public static void main(String... args) {
    try {
      Counter counter = new StupidCounter();
      executeCounterClient(counter);
      counter = new SynchronizedCounter();
      final long scTime = executeCounterClient(counter);
      counter = new AtomicCounter();
      final long acTime = executeCounterClient(counter);
      LOGGER.info(
          ()
              -> "Atomic counter: "
                     + String.format("%.2f", (double)scTime / (double)acTime)
                     + " times faster.");
      counter = new LockCounter();
      final long lcTime = executeCounterClient(counter);
      LOGGER.info(
          ()
              -> "Lock counter: "
                     + String.format("%.2f", (double)scTime / (double)lcTime)
                     + " times faster.");
      counter = new CASCounter();
      final long casTime = executeCounterClient(counter);
      LOGGER.info(
          ()
              -> "CAS counter: "
                     + String.format("%.2f", (double)scTime / (double)casTime)
                     + " times faster.");
      counter = new AtomicVHCounter();
      final long acvhTime = executeCounterClient(counter);
      LOGGER.info(
          ()
              -> "AtomicVH counter: "
                     + String.format("%.2f", (double)scTime / (double)acvhTime)
                     + " times faster.");
    } catch (InterruptedException ie) {
      LOGGER.severe(ie.getMessage());
    }
  }
}
