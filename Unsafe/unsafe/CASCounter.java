package unsafe;

import static unsafe.UnsafeUtils.*;
import java.util.logging.Logger;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings({"PMD.AvoidUsingVolatile", "restriction"})
public class CASCounter implements Counter {
  private static final Logger LOGGER = Logger.getLogger(CASCounter.class.getName());
  private volatile long counter;
  private Unsafe unsafe;
  private long offset;

  public CASCounter() {
    try {
      unsafe = getUnsafe();
      offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    } catch (ReflectiveOperationException roe) {
      LOGGER.severe(roe.getMessage());
    }
  }

  @Override
  public void increment() {
    long before = counter;
    while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
      before = counter;
    }
  }

  @Override
  public long get() {
    return counter;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "CASCounter(counter=" + this.counter + ", unsafe=" + this.unsafe + ", offset=" + this.offset + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof CASCounter)) return false;
    CASCounter other = (CASCounter) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.counter != other.counter) return false;
    Object this$unsafe = this.unsafe;
    Object other$unsafe = other.unsafe;
    if (this$unsafe == null ? other$unsafe != null : !this$unsafe.equals(other$unsafe)) return false;
    if (this.offset != other.offset) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof CASCounter;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $counter = this.counter;
    result = result * PRIME + (int) ($counter >>> 32 ^ $counter);
    Object $unsafe = this.unsafe;
    result = result * PRIME + ($unsafe == null ? 43 : $unsafe.hashCode());
    long $offset = this.offset;
    result = result * PRIME + (int) ($offset >>> 32 ^ $offset);
    return result;
  }
}
