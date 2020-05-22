package unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

class LockCounter implements Counter {
  private long counter;
  private final WriteLock lock = new ReentrantReadWriteLock(true).writeLock();

  @Override
  public void increment() {
  // not actually doing anything resource-intensive
    if (lock.tryLock()) {
    try {
    ++counter;
    }
    finally {
    lock.unlock();
    }
    }
    else
      increment();
  }

  @Override
  public long get() {
    return counter;
  }
}
