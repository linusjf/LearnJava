package unsafe;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

class LockCounter implements Counter {
  private long counter;
  private final WriteLock lock = new ReentrantReadWriteLock(true).writeLock();

  @Override
  public long increment() {
    lock.lock();
    counter++;
    lock.unlock();
    return counter;
  }

  @Override
  public long get() {
    return counter;
  }
}
