package threads;

import java.util.ArrayList;
import java.util.List;

public class FairLock {
  private boolean isLocked;
  private Thread lockingThread;
  private final List<QueueObject> waitingThreads = new ArrayList<>();

  public void lock() throws InterruptedException {
    QueueObject queueObject = new QueueObject();
    boolean isLockedForThisThread = true;
    synchronized (this) {
      waitingThreads.add(queueObject);
    }
    while (isLockedForThisThread) {
      synchronized (this) {
        isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
        if (!isLockedForThisThread) {
          isLocked = true;
          waitingThreads.remove(queueObject);
          lockingThread = Thread.currentThread();
          return;
        }
      }
      try {
        queueObject.doWait();
      } catch (InterruptedException e) {
        synchronized (this) {
          waitingThreads.remove(queueObject);
        }
        throw e;
      }
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void unlock() {
    synchronized (this) {
      if (this.lockingThread != Thread.currentThread()) {
        throw new IllegalMonitorStateException("Calling thread has not locked this lock");
      }
      isLocked = false;
      if (!waitingThreads.isEmpty()) waitingThreads.get(0).doNotify();
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof FairLock)) return false;
    FairLock other = (FairLock) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.isLocked != other.isLocked) return false;
    Object this$lockingThread = this.lockingThread;
    Object other$lockingThread = other.lockingThread;
    if (this$lockingThread == null ? other$lockingThread != null : !this$lockingThread.equals(other$lockingThread)) return false;
    Object this$waitingThreads = this.waitingThreads;
    Object other$waitingThreads = other.waitingThreads;
    if (this$waitingThreads == null ? other$waitingThreads != null : !this$waitingThreads.equals(other$waitingThreads)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof FairLock;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + (this.isLocked ? 79 : 97);
    Object $lockingThread = this.lockingThread;
    result = result * PRIME + ($lockingThread == null ? 43 : $lockingThread.hashCode());
    Object $waitingThreads = this.waitingThreads;
    result = result * PRIME + ($waitingThreads == null ? 43 : $waitingThreads.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "FairLock(isLocked=" + this.isLocked + ", lockingThread=" + this.lockingThread + ", waitingThreads=" + this.waitingThreads + ")";
  }
}
