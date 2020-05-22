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

  private static long executeCounterClient(Counter counter)
      throws InterruptedException {
    List<Future<?>> futures = new ArrayList<>();
    ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
    // creating instance of specific counter
    final long before = System.currentTimeMillis();
    for (int i = 0; i < NUM_OF_THREADS; i++) {
      Future<?> future =
          service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
      futures.add(future);
    }
    service.shutdown();
    service.awaitTermination(1, TimeUnit.MINUTES);
    long after = System.currentTimeMillis();
    LOGGER.info(() -> counter.getClass().getName());
    LOGGER.info(() -> "Counter result: " + counter.get());
    long timePassed = after - before;
    LOGGER.info(() -> "Time passed in ms:" + timePassed);
    boolean completed = areAllTasksComplete(futures);
    LOGGER.info(() -> "All tasks completed: " + completed);
    return timePassed;
  }

  public static void main(String... args) {
    try {
      Counter counter = new SynchronizedCounter();
      long scTime = executeCounterClient(counter);
      counter = new AtomicCounter();
      long acTime = executeCounterClient(counter);
      counter = new LockCounter();
      long lcTime = executeCounterClient(counter);
      double acFaster = (double)scTime / (double)acTime;
      double lcFaster = (double)scTime / (double)lcTime;
      LOGGER.info(()
                      -> "Atomic counter: " + String.format("%.2f", acFaster)
                             + " times faster.");
      LOGGER.info(()
                      -> "Lock counter: " + String.format("%.2f", lcFaster)
                             + " times faster.");
    } catch (InterruptedException ie) {
      LOGGER.severe(ie.getMessage());
    }
  }
}
