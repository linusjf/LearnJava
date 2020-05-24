package unsafe;

import java.util.logging.Logger;

public enum AtomicCounterTest {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(AtomicCounterTest.class.getName());

  private static long executeIncrement(Counter counter) {
    long startTime = System.currentTimeMillis();
    for (int idx = Integer.MIN_VALUE; idx < Integer.MAX_VALUE; idx++)
      counter.increment();
    long endTime = System.currentTimeMillis() - startTime;
    LOGGER.info(() -> counter.getClass().getName());
    LOGGER.info(() -> String.valueOf(counter.get()));
    LOGGER.info(() -> String.valueOf(endTime));
    return endTime;
  }

  private static void logPerformance(Counter counter,
                                     long origTime,
                                     long newTime) {
    LOGGER.info(
        ()
            -> counter.getClass().getName() +
            ": "
                   + String.format(
                       "%.2f", (1 - ((double)newTime / (double)origTime)) * 100)
                   + " percent faster");
  }

  public static void main(String... args) {

    Counter counter = new StupidCounter();
    executeIncrement(counter);
    counter = new SynchronizedCounter();
    final long endTime = executeIncrement(counter);
    counter = new AtomicCounter();
    final long endTime2 = executeIncrement(counter);
    logPerformance(counter, endTime2, endTime);
    counter = new LockCounter();
    final long endTime3 = executeIncrement(counter);
    logPerformance(counter, endTime3, endTime);
    counter = new CASCounter();
    final long endTime4 = executeIncrement(counter);
    logPerformance(counter, endTime4, endTime);
    counter = new AtomicVHCounter();
    final long endTime5 = executeIncrement(counter);
    logPerformance(counter, endTime5, endTime);
  }
}
