package trove;

import gnu.trove.iterator.TLongIterator;
import gnu.trove.set.hash.TLongHashSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public enum IteratorDemo {
  ;

  private static final Logger LOGGER =
      Logger.getLogger(IteratorDemo.class.getName());

  private static final int COUNT = 15_000;

  public static void main(String... args) {

    long start = System.currentTimeMillis();
    LOGGER.info(() -> "Start time: " + start);
    performJDK();
    LOGGER.info(() -> "Time taken: " + (System.currentTimeMillis() - start));
    long start2 = System.currentTimeMillis();
    LOGGER.info(() -> "Start time: " + start2);
    performTrove();
    LOGGER.info(() -> "Time taken: " + (System.currentTimeMillis() - start2));
  }

  private static void performJDK() {
    for (int i = 0; i < COUNT; i++)
      demonstrateJDKIterator();
  }

  private static void performTrove() {
    for (int i = 0; i < COUNT; i++)
      demonstrateIterationWithIterator();
  }

  /** Demonstrate "traditional" iteration of a Trove collection. */
  public static void demonstrateIterationWithIterator() {
    final TLongHashSet longs = new TLongHashSet();
    longs.add(15L);
    longs.add(6L);
    longs.add(12L);
    longs.add(13L);
    longs.add(2L);
    TLongIterator longIterator = longs.iterator();
    while (longIterator.hasNext()) {
      final long longValue = longIterator.next();
      LOGGER.fine(() -> "value = " + longValue);
    }
  }

  /** Demonstrate "traditional" iteration of a JDK collection. */
  public static void demonstrateJDKIterator() {
    final Set<Long> longs = new HashSet<>();
    longs.add(15L);
    longs.add(6L);
    longs.add(12L);
    longs.add(13L);
    longs.add(2L);
    Iterator<Long> longIterator = longs.iterator();
    while (longIterator.hasNext()) {
      final long longValue = longIterator.next();
      LOGGER.fine(() -> "value = " + longValue);
    }
  }
}
