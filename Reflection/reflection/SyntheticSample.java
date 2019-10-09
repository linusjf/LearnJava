package reflection;

import java.lang.reflect.Method;

public final class SyntheticSample {

  private SyntheticSample() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    SampleNestedClass nestObj = new SampleNestedClass();
    System.out.println("Nested Variable: " + nestObj.privateVariable);

    Class<?> c = nestObj.getClass();
    Method[] methods = c.getDeclaredMethods();

    // The synthetic method displays the receiver parameter.
    for (Method method : methods) {
      System.out.println(
        "method: " + method + " method.isSynthetic: " + method.isSynthetic()
      );
    }
  }

  private static final class SampleNestedClass {
    private String privateVariable = "A Private Variable!";
  }
}
