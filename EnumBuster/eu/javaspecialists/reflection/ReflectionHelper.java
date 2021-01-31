package eu.javaspecialists.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import sun.misc.Unsafe;

@SuppressWarnings("PMD")
public final class ReflectionHelper {

  private static final Unsafe UNSAFE;

  static {
    try {
      Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      UNSAFE = (Unsafe)theUnsafe.get(null);
    } catch (ReflectiveOperationException e) {
      throw new IllegalStateException(e);
    }
  }

  private ReflectionHelper() {
    throw new IllegalStateException("Private constructor");
  }

  public static Class<?>[] getAnonymousClasses(Class<?> clazz) {
    Collection<Class<?>> classes = new ArrayList<>();
    for (int i = 1;; i++) {
      try {
        classes.add(
            Class.forName(clazz.getName() + "$" + i,
                          false,
                          Thread.currentThread().getContextClassLoader()));
      } catch (ClassNotFoundException ignored) {
        break;
      }
    }
    for (Class<?> inner: clazz.getDeclaredClasses()) {
      Collections.addAll(classes, getAnonymousClasses(inner));
    }
    for (Class<?> anon: new ArrayList<>(classes)) {
      Collections.addAll(classes, getAnonymousClasses(anon));
    }
    return classes.toArray(new Class<?>[ 0 ]);
  }

  /**
   * Super dangerous and might have unintended side effects. Use only for testing, and then with
   * circumspection.
   *
   * @param field field
   * @param value value to be set
   * @throws ReflectiveOperationException exception
   */
  public static void setStaticFinalField(Field field, Object value)
      throws ReflectiveOperationException {
    if (!Modifier.isStatic(field.getModifiers())
        || !Modifier.isFinal(field.getModifiers()))
      throw new IllegalArgumentException("field should be final static");
    Object fieldBase = UNSAFE.staticFieldBase(field);
    long fieldOffset = UNSAFE.staticFieldOffset(field);
    UNSAFE.putObject(fieldBase, fieldOffset, value);
  }

  public static <E extends Enum<E>> E makeEnum(Class<E> enumType, String name)
      throws ReflectiveOperationException {
    E e = (E)UNSAFE.allocateInstance(enumType);
    Field nameField = Enum.class.getDeclaredField("name");
    nameField.setAccessible(true);
    nameField.set(e, name);
    return e;
  }
}
