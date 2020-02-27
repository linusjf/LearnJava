package trove;

import gnu.trove.list.linked.TLongLinkedList;

public enum TroveProcedure {
  ;

  public static void main(String... args) {
    demonstrateIterationWithProcedure();
  }

  /** Demonstrate iteration of a Trove collection using a Procedure. */
  public static void demonstrateIterationWithProcedure() {
    final TLongLinkedList longs = new TLongLinkedList();
    longs.add(15L);
    longs.add(6L);
    longs.add(12L);
    longs.add(13L);
    longs.add(2L);
    longs.forEach(new StandardOutputLongProcedure());
  }
}
