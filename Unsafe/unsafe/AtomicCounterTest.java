package unsafe;

import java.util.logging.Logger;

public enum AtomicCounterTest {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(AtomicCounterTest.class.getName());

  private static long executeIncrement(Counter counter) {
    long startTime = System.nanoTime();
    for (int idx = Integer.MIN_VALUE; idx < Integer.MAX_VALUE; idx++)
      counter.increment();
    long endTime = System.nanoTime() - startTime;
    LOGGER.info(() -> counter.getClass().getName());
    LOGGER.info(() -> String.valueOf(counter.get()));
    LOGGER.info(() -> String.valueOf(endTime));
    return endTime;
  }

  public static void main(String... args) {

    Counter counter = new StupidCounter();
    executeIncrement(counter);
    counter = new SynchronizedCounter();
    final long endTime = executeIncrement(counter);
    counter = new AtomicCounter();
    final long endTime2 = executeIncrement(counter);
    counter = new LockCounter();
    final long endTime3 = executeIncrement(counter);
    counter = new CASCounter();
    final long endTime4 = executeIncrement(counter);
    LOGGER.info(
        ()
            -> "Lock counter: "
                   + String.format(
                       "%.2f", (1 - ((float)endTime3 / (float)endTime)) * 100)
                   + " percent faster");
    LOGGER.info(
        ()
            -> "Atomic counter: "
                   + String.format(
                       "%.2f", (1 - ((float)endTime2 / (float)endTime)) * 100)
                   + " percent faster");
    LOGGER.info(
        ()
            -> "CAS counter: "
                   + String.format(
                       "%.2f", (1 - ((float)endTime4 / (float)endTime)) * 100)
                   + " percent faster");
  }
}
