package test;

@SuppressWarnings("PMD.SystemPrintln")
public class TestJIT {

  private TestJIT() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    MyClass obj = new MyClass();
    for (int i = 0; i < 10_000; i++)
      benchVMethod(obj);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ie) {
      // empty catch block
    }
  }

  public static void benchVMethod(MyClass obj) {
    obj.vmethod();
  }

  private static class MyClass {
    public final void vmethod() {
      if (System.currentTimeMillis() == 0)
        System.out.println("call to vmethod");
    }
  }
}
