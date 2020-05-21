package unsafe;

public final class SynchronizedCounter implements Counter {
  private int i;

  @Override
  public int increment() {
    synchronized (this) {
      return ++i;
    }
  }

  @Override
  public int get() {
    synchronized (this) {
      return i;
    }
  }
}
