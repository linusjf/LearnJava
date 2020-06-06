package unsafe;

public final class SynchronizedCounter implements Counter {
  
  private long i;
  private Object syncOn = new Object();

  @Override
  public void increment() {
    synchronized (syncOn) {
      ++i;
    }
  }

  @Override
  public long get() {
    synchronized (syncOn) {
      return i;
    }
  }
}
