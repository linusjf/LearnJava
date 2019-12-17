package reflection;

import java.lang.reflect.Method;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class SyntheticSample {
  private SyntheticSample() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    SampleNestedClass nestObj = new SampleNestedClass();
    System.out.println("Nested Variable: " + nestObj.privateVariable);
    Class<?> c = nestObj.getClass();
    displaySyntheticity(c);
  }

  private static void displaySyntheticity(Class<?> c) {
    Method[] methods = c.getDeclaredMethods();

    // The synthetic method displays the receiver parameter.
    for (Method method: methods)
      System.out.println("method: " + method
                         + " method.isSynthetic: " + method.isSynthetic());
  }

  @SuppressWarnings("PMD.FinalFieldCouldBeStatic")
  private static final class SampleNestedClass {
    private final String privateVariable = "A Private Variable!";
  }
}
