package trove;

import gnu.trove.TCollections;
import gnu.trove.list.TIntList;
import gnu.trove.list.linked.TIntLinkedList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public enum ListDemo {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(ListDemo.class.getName());
  private static final int COUNT = 15_000;

  public static void main(String... args) {
    long start = System.currentTimeMillis();
    LOGGER.info(() -> "Start time: " + start);
    for (int i = 0; i < COUNT; i++)
      demonstrateJDKCollectionsClass();
    LOGGER.info(() -> "Time taken: " + (System.currentTimeMillis() - start));
    long start2 = System.currentTimeMillis();
    LOGGER.info(() -> "Start time: " + start2);
    for (int i = 0; i < COUNT; i++)
      demonstrateTroveCollectionsClass();
    LOGGER.info(() -> "Time taken: " + (System.currentTimeMillis() - start2));
  }

  /** Demonstrate one of Trove's "equivalent"s of the java.util.Collections class. */
  public static void demonstrateTroveCollectionsClass() {
    final TIntLinkedList integers = new TIntLinkedList();
    integers.add(5);
    integers.add(7);
    integers.add(3);
    integers.add(1);
    final TIntList unmodifiableIntegers =
        TCollections.unmodifiableList(integers);
    try {
      unmodifiableIntegers.add(15);
    } catch (UnsupportedOperationException ex) {
      LOGGER.fine(() -> "\tException caught: " + ex);
    }
  }

  /** Demonstrate one of java.util.Collections class. */
  public static void demonstrateJDKCollectionsClass() {
    final List<Integer> integers = new LinkedList<>();
    integers.add(5);
    integers.add(7);
    integers.add(3);
    integers.add(1);
    final List<Integer> unmodifiableIntegers =
        Collections.unmodifiableList(integers);
    try {
      unmodifiableIntegers.add(15);
    } catch (UnsupportedOperationException ex) {
      LOGGER.severe(() -> "\tException caught: " + ex);
    }
  }
}
