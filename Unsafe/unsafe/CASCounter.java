package unsafe;

import static unsafe.UnsafeUtils.*;

import java.util.logging.Logger;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings("PMD.AvoidUsingVolatile")
public class CASCounter implements Counter {
  private static final Logger LOGGER =
      Logger.getLogger(CASCounter.class.getName());

  private volatile long counter;
  private Unsafe unsafe;
  private long offset;

  public CASCounter() {
    try {
      unsafe = getUnsafe();
      offset = unsafe.objectFieldOffset(
          CASCounter.class.getDeclaredField("counter"));
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
}
