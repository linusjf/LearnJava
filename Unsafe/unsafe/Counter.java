package unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.misc.Unsafe; // NOPMD

interface Counter {

  Class<?> UNSAFE_CLASS = Unsafe.class;

  void increment();

  long get();

  @SuppressWarnings("PMD.LawOfDemeter")
  static Unsafe getUnsafe() throws ReflectiveOperationException {
    return AccessController.doPrivileged((PrivilegedAction<Unsafe>)() -> {
      try {
        Field f = UNSAFE_CLASS.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe)f.get(null);
      } catch (ReflectiveOperationException ex) {
        throw new AssertionError(ex);
      }
    });
  }
}
