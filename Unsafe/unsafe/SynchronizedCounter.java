package unsafe;

public final class SynchronizedCounter implements Counter {
  private long i;

  @Override
  public void increment() {
    synchronized (this) {
      ++i;
    }
  }

  @Override
  public long get() {
    synchronized (this) {
      return i;
    }
  }
}
