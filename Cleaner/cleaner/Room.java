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
    @SuppressWarnings("PMD.SystemPrintln")
    public void run() {
      System.out.printf("Cleaning room with %d junk piles.%n", numJunkPiles);
      numJunkPiles = 0;
    }
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Room(state=" + this.state + ", cleanable=" + this.cleanable + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Room)) return false;
    Room other = (Room) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$state = this.state;
    Object other$state = other.state;
    if (this$state == null ? other$state != null : !this$state.equals(other$state)) return false;
    Object this$cleanable = this.cleanable;
    Object other$cleanable = other.cleanable;
    if (this$cleanable == null ? other$cleanable != null : !this$cleanable.equals(other$cleanable)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Room;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $state = this.state;
    result = result * PRIME + ($state == null ? 43 : $state.hashCode());
    Object $cleanable = this.cleanable;
    result = result * PRIME + ($cleanable == null ? 43 : $cleanable.hashCode());
    return result;
  }
}
