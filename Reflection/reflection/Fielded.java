package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;

public final class Fielded {
  private static String stringer = "this is a String called stringer";
  private static Class<? extends String> stringGetClass = stringer.getClass();
  private static Class<String> stringclass = String.class;

  private Fielded() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings({"PMD.CompareObjectsWithEquals", "checkstyle:executablestatementcount"})
  public static void main(String... args) {
    try {
      System.out.println(stringGetClass == stringclass);
      System.out.println(stringGetClass.equals(stringclass));
      Field[] fields = stringclass.getDeclaredFields();
      for (Field field : fields) {
        System.out.println("*************************");
        System.out.println("Name: " + field.getName());
        System.out.println("Type: " + field.getType());
        int modifiers = field.getModifiers();
        System.out.println(Modifier.toString(modifiers & Modifier.fieldModifiers()));
        if (Modifier.isStatic(modifiers)) {
          System.out.println("isAccessible: " + field.canAccess(null));
          if (field.canAccess(null))
            System.out.println("Get: " + field.get(null));
        } else {
          System.out.println("isAccessible: " + field.canAccess(stringer));
          if (field.canAccess(stringer))
            System.out.println("Get: " + field.get(stringer));
        }
      }
      checkForHashCode(stringGetClass, stringer);
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe);
    }
  }

  private static void checkForHashCode(Class<?> cls, Object obj)
      throws ReflectiveOperationException {
    try {
      cls.getField("hashCode");
    } catch (NoSuchFieldException nsfe) {
      System.err.println(nsfe);
    }
    Field fieldHashCode = cls.getDeclaredField("hash");
    try {
      fieldHashCode.get(obj);
    } catch (IllegalAccessException iae) {
      System.err.println(iae);
    }

    AccessController.doPrivileged((PrivilegedAction<?>) () -> {
      fieldHashCode.setAccessible(true);
      return null;
    });

    printHashCodeField(fieldHashCode, obj);
  }

  private static void printHashCodeField(Field field, Object object)
      throws ReflectiveOperationException {
    Object value = field.get(object);
    int valueInt = field.getInt(object);
    System.out.println(value);
    System.out.println(valueInt);
  }
}
