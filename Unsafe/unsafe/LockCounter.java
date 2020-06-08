package unsafe;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

class LockCounter implements Counter {
  private long counter;
  private final WriteLock lock = new ReentrantReadWriteLock(true).writeLock();
  private final Random random = new Random();

  @SuppressWarnings("PMD.LawOfDemeter")
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

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "LockCounter(counter=" + this.counter + ", lock=" + this.lock + ", random=" + this.random + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof LockCounter)) return false;
    LockCounter other = (LockCounter) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.counter != other.counter) return false;
    Object this$lock = this.lock;
    Object other$lock = other.lock;
    if (this$lock == null ? other$lock != null : !this$lock.equals(other$lock)) return false;
    Object this$random = this.random;
    Object other$random = other.random;
    if (this$random == null ? other$random != null : !this$random.equals(other$random)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof LockCounter;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $counter = this.counter;
    result = result * PRIME + (int) ($counter >>> 32 ^ $counter);
    Object $lock = this.lock;
    result = result * PRIME + ($lock == null ? 43 : $lock.hashCode());
    Object $random = this.random;
    result = result * PRIME + ($random == null ? 43 : $random.hashCode());
    return result;
  }
}
