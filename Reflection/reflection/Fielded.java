package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class Fielded {

  private Fielded() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.CompareObjectsWithEquals")
  public static void main(String... args) {
    try {
      String stringer = "this is a String called stringer";
      Class<? extends String> stringGetClass = stringer.getClass();
      Class<String> stringclass = String.class;
      System.out.println(stringGetClass == stringclass);
      System.out.println(stringGetClass.equals(stringclass));
      Field[] fields = stringclass.getDeclaredFields();
      for (Field field: fields) {
        System.out.println("*************************");
        System.out.println("Name: " + field.getName());
        System.out.println("Type: " + field.getType());
        int modifiers = field.getModifiers();
        System.out.println(
            Modifier.toString(modifiers & Modifier.fieldModifiers()));
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
      try {
        stringclass.getField("hashCode");
      } catch (NoSuchFieldException nsfe) {
        System.err.println(nsfe);
      }
      Field fieldHashCode = stringclass.getDeclaredField("hash");
      try {
        fieldHashCode.get(stringer);
      } catch (IllegalAccessException iae) {
        System.err.println(iae);
      }
      fieldHashCode.setAccessible(true);

      Object value = fieldHashCode.get(stringer);
      int valueInt = fieldHashCode.getInt(stringer);
      System.out.println(value);
      System.out.println(valueInt);
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe);
    }
  }
}
