package unsafe;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

class LockCounter implements Counter {
  private long counter;
  private final WriteLock lock = new ReentrantReadWriteLock(true).writeLock();
  private final Random random = new Random();

  @Override
  public void increment() {
    try {
      while (true) {
        boolean locked = lock.tryLock();
        if (locked) {
          try {
            ++counter;
            return;
          } finally {
            lock.unlock();
          }
        }
        Thread.sleep(random.nextInt(10));
      }
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public long get() {
    return counter;
  }
}
