package unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public final class AtomicCounter implements Counter {
  private static final Unsafe UNSAFE;
  private static final long VALUE_OFFSET;

  private volatile int value;

  static {
    try {
      Field f = Unsafe.class.getDeclaredField("theUnsafe");
      f.setAccessible(true);
      UNSAFE = (Unsafe)f.get(null);
      VALUE_OFFSET = UNSAFE.objectFieldOffset(
          AtomicCounter.class.getDeclaredField("value"));
    } catch (ReflectiveOperationException ex) {
      throw new AssertionError(ex);
    }
  }

  @Override
  public int increment() {
    return UNSAFE.getAndAddInt(this, VALUE_OFFSET, 1) + 1;
  }

  @Override
  public int get() {
    return value;
  }
}
