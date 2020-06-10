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
        throw new IllegalMonitorStateException("Calling thread has not locked this lock");
      }
      isLocked = false;
      notifyAll();
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Lock)) return false;
    Lock other = (Lock) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.isLocked != other.isLocked) return false;
    Object this$lockingThread = this.lockingThread;
    Object other$lockingThread = other.lockingThread;
    if (this$lockingThread == null ? other$lockingThread != null : !this$lockingThread.equals(other$lockingThread)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Lock;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + (this.isLocked ? 79 : 97);
    Object $lockingThread = this.lockingThread;
    result = result * PRIME + ($lockingThread == null ? 43 : $lockingThread.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Lock(isLocked=" + this.isLocked + ", lockingThread=" + this.lockingThread + ")";
  }
}
