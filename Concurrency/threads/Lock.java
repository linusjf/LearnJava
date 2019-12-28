package threads;

@SuppressWarnings("PMD.ShortClassName")
public class Lock {
  private boolean isLocked;
  private Thread lockingThread;

  public void lock() throws InterruptedException {
    synchronized (this) {
      while (isLocked) {
        wait();
      }
      isLocked = true;
      lockingThread = Thread.currentThread();
    }
  }

  public void unlock() {
    synchronized (this) {
      if (this.lockingThread != Thread.currentThread()) {
        throw new IllegalMonitorStateException(
            "Calling thread has not locked this lock");
      }
      isLocked = false;
      notifyAll();
    }
  }
}
