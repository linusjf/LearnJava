package unsafe;

import java.util.logging.Logger;

public enum AtomicCounterTest {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(AtomicCounterTest.class.getName());

  public static void main(String... args) {

    long startTime = System.nanoTime();
    SynchronizedCounter sc = new SynchronizedCounter();
    for (int idx = Integer.MIN_VALUE; idx < Integer.MAX_VALUE; idx++)
      sc.increment();
    long endTime = System.nanoTime() - startTime;
    LOGGER.info(() -> String.valueOf(sc.get()));
    LOGGER.info(() -> String.valueOf(endTime));

    long startTime3 = System.nanoTime();
    LockCounter lc = new LockCounter();
    for (int idx = Integer.MIN_VALUE; idx < Integer.MAX_VALUE; idx++)
      lc.increment();
    long endTime3 = System.nanoTime() - startTime3;
    LOGGER.info(() -> String.valueOf(lc.get()));
    LOGGER.info(() -> String.valueOf(endTime3));
    float faster2 = (1 - ((float)endTime3 / (float)endTime)) * 100;
    LOGGER.info(() -> String.format("%.2f", faster2) + " percent faster");

    long startTime2 = System.nanoTime();
    AtomicCounter ac = new AtomicCounter();
    for (int idx = Integer.MIN_VALUE; idx < Integer.MAX_VALUE; idx++)
      ac.increment();
    long endTime2 = System.nanoTime() - startTime2;
    LOGGER.info(() -> String.valueOf(ac.get()));
    LOGGER.info(() -> String.valueOf(endTime2));
    float faster = (1 - ((float)endTime2 / (float)endTime)) * 100;
    LOGGER.info(() -> String.format("%.2f", faster) + " percent faster");
  }
}
