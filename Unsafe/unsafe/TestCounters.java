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

  private static List<Future<?>> submit(Counter counter,
                                        ExecutorService service) {
    List<Future<?>> futures = new ArrayList<>();
    for (int i = 0; i < NUM_OF_THREADS; i++)
      futures.add(
          service.submit(new CounterClient(counter, NUM_OF_INCREMENTS)));
    return futures;
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnalysis"})
  private static long executeCounterClient(Counter counter)
      throws InterruptedException {
    ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
    // creating instance of specific counter
    final long before = System.currentTimeMillis();
    final List<Future<?>> futures = submit(counter, service);
    service.shutdown();
    service.awaitTermination(1, TimeUnit.MINUTES);
    LOGGER.info(()
                    -> counter.getClass().getName() + ": All tasks completed: "
                           + areAllTasksComplete(futures));
    long after = System.currentTimeMillis();
    LOGGER.info(() -> counter.getClass().getName());
    LOGGER.info(() -> "Counter result: " + counter.get());
    long timePassed = after - before;
    LOGGER.info(() -> "Time passed in ms:" + timePassed);
    return timePassed;
  }

  private static void logPerformance(Counter counter,
                                     long origTime,
                                     long newTime) {

    LOGGER.info(
        ()
            -> counter.getClass().getName() + ": "
                   + String.format("%.2f", (double)origTime / (double)newTime)
                   + " times faster.");
  }

  public static void main(String... args) {
    try {
      Counter counter = new StupidCounter();
      executeCounterClient(counter);
      counter = new SynchronizedCounter();
      final long scTime = executeCounterClient(counter);
      counter = new AtomicCounter();
      final long acTime = executeCounterClient(counter);
      logPerformance(counter, scTime, acTime);
      counter = new LockCounter();
      final long lcTime = executeCounterClient(counter);
      logPerformance(counter, scTime, lcTime);
      counter = new CASCounter();
      final long casTime = executeCounterClient(counter);
      logPerformance(counter, scTime, casTime);
      counter = new AtomicVHCounter();
      final long acvhTime = executeCounterClient(counter);
      logPerformance(counter, scTime, acvhTime);
    } catch (InterruptedException ie) {
      LOGGER.severe(ie.getMessage());
    }
  }
}
