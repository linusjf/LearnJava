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

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "SynchronizedCounter(i=" + this.i + ", syncOn=" + this.syncOn + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof SynchronizedCounter)) return false;
    SynchronizedCounter other = (SynchronizedCounter) o;
    if (this.i != other.i) return false;
    Object this$syncOn = this.syncOn;
    Object other$syncOn = other.syncOn;
    if (this$syncOn == null ? other$syncOn != null : !this$syncOn.equals(other$syncOn)) return false;
    return true;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $i = this.i;
    result = result * PRIME + (int) ($i >>> 32 ^ $i);
    Object $syncOn = this.syncOn;
    result = result * PRIME + ($syncOn == null ? 43 : $syncOn.hashCode());
    return result;
  }
}
