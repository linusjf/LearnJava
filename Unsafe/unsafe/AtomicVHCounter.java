package unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class AtomicVHCounter implements Counter {
  private static final VarHandle VH;
  private volatile long value; // NOPMD

  static {
    try {
      MethodHandles.Lookup l = MethodHandles.lookup();
      VH = l.findVarHandle(AtomicVHCounter.class, "value", long.class);
    } catch (ReflectiveOperationException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  @Override
  public void increment() {
    long i;
    do {
      i = (long) VH.getVolatile(this);
    } while (!VH.compareAndSet(this, i, i + 1));
  }

  @Override
  public long get() {
    return value;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "AtomicVHCounter(value=" + this.value + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof AtomicVHCounter)) return false;
    AtomicVHCounter other = (AtomicVHCounter) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.value != other.value) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof AtomicVHCounter;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $value = this.value;
    result = result * PRIME + (int) ($value >>> 32 ^ $value);
    return result;
  }
}
