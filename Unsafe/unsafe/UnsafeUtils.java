package unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;
import sun.misc.Unsafe; // NOPMD

public final class UnsafeUtils {

  static Class<?> unsafeClass = Unsafe.class;
  static volatile Unsafe unsafe;  // NOPMD

  private UnsafeUtils() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings({"PMD.LawOfDemeter",
                     "PMD.NonThreadSafeSingleton",
                     "PMD.AvoidAccessibilityAlteration"})
  public static Unsafe
  getUnsafe() throws ReflectiveOperationException {
    if (unsafe == null) {
      synchronized (UnsafeUtils.class) {
        if (unsafe == null)
          unsafe =
              AccessController.doPrivileged((PrivilegedAction<Unsafe>)() -> {
                try {
                  Constructor<Unsafe> c = Unsafe.class.getDeclaredConstructor();
                  c.setAccessible(true);
                  return c.newInstance();
                } catch (ReflectiveOperationException ex) {
                  throw new ExceptionInInitializerError(ex);
                }
              });
      }
    }
    return unsafe;
  }

  public static long sizeOf(Object o) throws ReflectiveOperationException {
    Set<Field> fields = new HashSet<>();
    Class<?> c = o.getClass();
    while (c != Object.class) {
      for (Field f: c.getDeclaredFields()) {
        if ((f.getModifiers() & Modifier.STATIC) == 0) {
          fields.add(f);
        }
      }
      c = c.getSuperclass();
    }

    // get offset
    long maxSize = getMaxFieldOffset(fields);

    // padding
    return (maxSize / 8 + 1) * 8;
  }

  private static long getMaxFieldOffset(Set<Field> fields)
      throws ReflectiveOperationException {
    Unsafe u = getUnsafe();
    return getMaxFieldOffset(u, fields);
  }

  private static long getMaxFieldOffset(Unsafe u, Set<Field> fields)
      throws ReflectiveOperationException {
    long maxSize = 0;
    for (Field f: fields) {
      long offset = u.objectFieldOffset(f);
      if (offset > maxSize) {
        maxSize = offset;
      }
    }
    return maxSize;
  }

  private static long normalize(int value) {
    if (value >= 0)
      return value;
    return (~0L >>> 32) & value;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static Object shallowCopy(Object obj)
      throws ReflectiveOperationException {
    long size = sizeOf(obj);
    long start = toAddress(obj);
    Unsafe u = getUnsafe();
    long address = u.allocateMemory(size);
    u.copyMemory(start, address, size);
    return fromAddress(address);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static long toAddress(Object obj) throws ReflectiveOperationException {
    Object[] array = new Object[] {obj};
    Unsafe u = getUnsafe();
    long baseOffset = u.arrayBaseOffset(Object[].class);
    return normalize(u.getInt(array, baseOffset));
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static Object fromAddress(long address)
      throws ReflectiveOperationException {
    Object[] array = new Object[] {null};
    Unsafe u = getUnsafe();
    long baseOffset = u.arrayBaseOffset(Object[].class);
    u.putLong(array, baseOffset, address);
    return array[0];
  }
}
