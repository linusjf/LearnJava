package unsafe;

public class StupidCounter implements Counter {
  private long counter;

  @Override
  public void increment() {
    ++counter;
  }

  @Override
  public long get() {
    return counter;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "StupidCounter(counter=" + this.counter + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof StupidCounter)) return false;
    StupidCounter other = (StupidCounter) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.counter != other.counter) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof StupidCounter;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $counter = this.counter;
    result = result * PRIME + (int) ($counter >>> 32 ^ $counter);
    return result;
  }
}
