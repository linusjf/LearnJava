package unsafe;

import static unsafe.UnsafeUtils.*;
import java.util.logging.Logger;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings({"PMD.AvoidUsingVolatile", "restriction"})
public final class AtomicCounter implements Counter {
  private static final Logger LOGGER = Logger.getLogger(AtomicCounter.class.getName());
  private static Unsafe unsafeObject;
  private static long valueOffset;
  private volatile long value;

  static {
    try {
      unsafeObject = getUnsafe();
      valueOffset = unsafeObject.objectFieldOffset(AtomicCounter.class.getDeclaredField("value"));
    } catch (ReflectiveOperationException roe) {
      LOGGER.severe(roe.getMessage());
    }
  }

  @Override
  public void increment() {
    unsafeObject.getAndAddLong(this, valueOffset, 1L);
  }

  @Override
  public long get() {
    return value;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "AtomicCounter(value=" + this.value + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof AtomicCounter)) return false;
    AtomicCounter other = (AtomicCounter) o;
    if (this.value != other.value) return false;
    return true;
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
