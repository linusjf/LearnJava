package unsafe;

import static unsafe.UnsafeUtils.*;

import java.util.logging.Logger;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings("PMD.AvoidUsingVolatile")
public final class AtomicCounter implements Counter {
  private static final Logger LOGGER =
      Logger.getLogger(AtomicCounter.class.getName());
  private static Unsafe unsafeObject;
  private static long valueOffset;
  private volatile long value;

  static {
    try {
      unsafeObject = getUnsafe();
      valueOffset = unsafeObject.objectFieldOffset(
          AtomicCounter.class.getDeclaredField("value"));
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
}
