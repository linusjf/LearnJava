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

  public void unlock() {
    synchronized (this) {
      if (this.lockingThread != Thread.currentThread()) {
        throw new IllegalMonitorStateException("Calling thread has not locked this lock");
      }
      isLocked = false;
      if (!waitingThreads.isEmpty()) {
        waitingThreads.get(0).doNotify();
      }
    }
  }
}
