package reflection;

import java.lang.reflect.Field;

public final class Fielded {

  private Fielded() {
    throw new IllegalStateException("Private constructor");
  }

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
        // values
        if (field.canAccess(stringer)) {
          System.out.println("Get: " + field.get(stringer));
        }
        System.out.println("Modifiers:" + field.getModifiers());
        System.out.println("isAccessible: " + field.canAccess(stringer));
      }
      try {
        stringclass.getField("hashCode");
        // exception
      } catch (NoSuchFieldException nsfe) {
        System.err.println(nsfe);
      }
      Field fieldHashCode = stringclass.getDeclaredField("hash");
      try {
        fieldHashCode.get(stringer);
        // this produces an java.lang.IllegalAccessException
      } catch (IllegalAccessException iae) {
        System.err.println(iae);
      }
      // we change the visibility
      fieldHashCode.setAccessible(true);
      // and we can access it

      Object value = fieldHashCode.get(stringer);
      int valueInt = fieldHashCode.getInt(stringer);
      System.out.println(value);
      System.out.println(valueInt);

    } catch (ReflectiveOperationException roe) {
      System.err.println(roe);
    }
  }
}
