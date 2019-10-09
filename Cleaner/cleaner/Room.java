package cleaner;

import java.lang.ref.Cleaner;

// An autocloseable class using a cleaner as a safety net
/**
 * Describe class <code>Room</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
public class Room implements AutoCloseable {
  private static final Cleaner CLEANER = Cleaner.create();

  // The state of this room, shared with our cleanable
  private final State state; // NOPMD

  // Our cleanable. Cleans the room when it’s eligible for gc
  private final Cleaner.Cleanable cleanable;

  /**
   * Creates a new <code>Room</code> instance.
   *
   * @param numJunkPiles an <code>int</code> value
   */
  public Room(int numJunkPiles) {
    state = new State(numJunkPiles); // NOPMD
    cleanable = CLEANER.register(this, state);
  }

  @Override
  public void close() {
    cleanable.clean();
  }

  // Resource that requires cleaning. Must not refer to Room!
  private static class State implements Runnable {
    private int numJunkPiles;

    // Number of junk piles in this room
    /**
     * Creates a new <code>State</code> instance.
     *
     * @param numJunkPiles an <code>int</code> value
     */
    State(int numJunkPiles) {
      this.numJunkPiles = numJunkPiles;
    }

    // Invoked by close method or cleaner
    @Override
    public void run() {
      System.out.println("Cleaning room");
      numJunkPiles = 0;
    }
  }
}
