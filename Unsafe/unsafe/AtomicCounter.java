package unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings("PMD.AvoidUsingVolatile")
public final class AtomicCounter implements Counter {
  private static Unsafe unsafeObject;
  private static long valueOffset;

  private volatile long value;

  static {
    AccessController.doPrivileged((PrivilegedAction<Object>)() -> {
      try {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        unsafeObject = (Unsafe)f.get(null);
        valueOffset = unsafeObject.objectFieldOffset(
            AtomicCounter.class.getDeclaredField("value"));
      } catch (ReflectiveOperationException ex) {
        throw new AssertionError(ex);
      }
      return null;
    });
  }

  @Override
  public long increment() {
    return unsafeObject.getAndAddLong(this, valueOffset, 1L) + 1L;
  }

  @Override
  public long get() {
    return value;
  }
}
