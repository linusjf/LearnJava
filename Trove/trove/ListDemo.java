package trove;

import gnu.trove.TCollections;
import gnu.trove.list.TIntList;
import gnu.trove.list.linked.TIntLinkedList;
import java.util.logging.Logger;

public enum ListDemo {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(ListDemo.class.getName());

  public static void main(String... args) {
    demonstrateTroveCollectionsClass();
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
      LOGGER.severe(() -> "\tException caught: " + ex);
    }
  }
}
