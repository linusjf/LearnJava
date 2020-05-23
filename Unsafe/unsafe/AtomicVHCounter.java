package unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class AtomicVHCounter implements Counter {
  private static final VarHandle VH;
  private volatile long value;

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
      i = (long)VH.getVolatile(this);
    } while (!VH.compareAndSet(this, i, i + 1));
  }

  @Override
  public long get() {
    return value;
  }
}
