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

  private static Unsafe getUnsafe() throws ReflectiveOperationException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe)f.get(null);
  }

  static {
    AccessController.doPrivileged((PrivilegedAction<Object>)() -> {
      try {
        unsafeObject = getUnsafe();
        valueOffset = unsafeObject.objectFieldOffset(
            AtomicCounter.class.getDeclaredField("value"));
      } catch (ReflectiveOperationException ex) {
        throw new AssertionError(ex);
      }
      return null;
    });
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
