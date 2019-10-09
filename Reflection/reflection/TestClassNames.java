package reflection;

public enum TestClassNames {
  ;
  private static void showClass(Class<?> c) {
    System.out.println("getName():          " + c.getName());
    System.out.println("getCanonicalName(): " + c.getCanonicalName());
    System.out.println("getSimpleName():    " + c.getSimpleName());
    System.out.println("getTypeName():    " + c.getTypeName());
    System.out.println("toString():         " + c.toString());
    System.out.println();
  }

  private static void show(Runnable r) {
    showClass(r.getClass());
    showClass(java.lang.reflect.Array.newInstance(r.getClass(), 1).getClass());
  // Obtains an array class of a lambda base type.
  }

  public static void main(String[] args) {
    class LocalClass {
      // empty local class
    }

    showClass(void.class);
    showClass(int.class);
    showClass(String.class);
    showClass(Runnable.class);
    showClass(SomeEnum.class);
    showClass(SomeAnnotation.class);
    showClass(int[].class);
    showClass(String[].class);
    showClass(NestedClass.class);
    showClass(InnerClass.class);
    showClass(LocalClass.class);
    showClass(LocalClass[].class);
    Object anonymous = new java.io.Serializable() {};
    showClass(anonymous.getClass());
    showClass(
      java.lang.reflect.Array.newInstance(anonymous.getClass(), 1).getClass()
    );

    // Obtains an array class of an anonymous base type.
    show(() -> {});
  }

  public static class NestedClass {
    // static empty class
  }

  public class InnerClass {
    // empty instance class
  }
}

@interface SomeAnnotation {
}

@SuppressWarnings("checkstyle:onetoplevelclass")
enum SomeEnum {
  BLUE, YELLOW, RED, ;
}
