package unsafe;

public final class SynchronizedCounter implements Counter {
  private long i;

  @Override
  public long increment() {
    synchronized (this) {
      return ++i;
    }
  }

  @Override
  public long get() {
    synchronized (this) {
      return i;
    }
  }
}
