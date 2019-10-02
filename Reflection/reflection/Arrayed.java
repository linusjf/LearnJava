package reflection;

import java.lang.reflect.Array;

public enum Arrayed {
  ;

  @SuppressWarnings("unused")
  public static void main(String... args) {
    try {
      String[] strArrayOne = (String[])Array.newInstance(String.class, 10);
      // it contains utility methods for setting values
      Array.set(strArrayOne, 0, "member0");
      Array.set(strArrayOne, 1, "member1");
      Array.set(strArrayOne, 9, "member9");
      // and for getting values as well
      System.out.println("strArrayOne[0] : " + Array.get(strArrayOne, 0));
      System.out.println("strArrayOne[1] : " + Array.get(strArrayOne, 1));
      System.out.println("strArrayOne[3] (not initialized) : "
                         + Array.get(strArrayOne, 3));
      System.out.println("strArrayOne[9] : " + Array.get(strArrayOne, 9));
      // also methods to retrieve the lenght of the array
      System.out.println("length strArrayOne: " + Array.getLength(strArrayOne));
      // primitive types work as well
      int[] intArrayOne = (int[])Array.newInstance(int.class, 10);
      Array.set(intArrayOne, 0, 1);
      Array.set(intArrayOne, 1, 2);
      Array.set(intArrayOne, 9, 10);
      // and specific getters and setters for primitive types
      for (int i = 0; i < Array.getLength(intArrayOne); ++i) {
        System.out.println("intArrayOne[" + i
                           + "] : " + Array.getInt(intArrayOne, i));
      }

      // retrieve the class from an instance
      Class<String[]> stringArrayClassUsingInstance = String[].class;
      System.out.println("stringArrayClassUsingInstance is array: "
                         + stringArrayClassUsingInstance.isArray());
      // using class for name and passing [I
      Class<?> byteArrayUsingClassForName = Class.forName("[B");
      System.out.println("byteArrayUsingClassForName is array: "
                         + byteArrayUsingClassForName.isArray());
      // or [Ljava.lang.String
      Class<?> stringArrayClassUsingClassForName =
          Class.forName("[Ljava.lang.String;");
      System.out.println("stringArrayClassUsingClassForName is array: "
                         + stringArrayClassUsingClassForName.isArray());
      Class<? extends Object> stringArrayClassUsingDoubleLoop =
          Array.newInstance(String.class, 0).getClass();
      System.out.println("stringArrayClassUsingClassForName is array: "
                         + stringArrayClassUsingDoubleLoop.isArray());

      // The above makes sense if you're trying
      // to get the class name of a primitive type array.
      try {
        Class<? extends Object> byteArrayClassUsingClassForName =
            Class.forName("byte");
      } catch (ClassNotFoundException cnfe) {
        System.err.println(cnfe);
      }
      Class<? extends Object> byteClass = byte.class;
      // Now get the array class
      Class<? extends Object> byteArrayClassUsingDoubleLoop =
          Array.newInstance(byteClass, 0).getClass();
      System.out.println("byteArrayClassUsingClassForName is array: "
                         + byteArrayClassUsingDoubleLoop.isArray());
      System.out.println(byteArrayClassUsingDoubleLoop);

      System.out.println("Component class: "
                         + byteArrayClassUsingDoubleLoop.getComponentType());
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe);
    }
  }
}
