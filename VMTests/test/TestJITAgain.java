package test;

@SuppressWarnings("PMD.SystemPrintln")
public final class TestJITAgain {
  private TestJITAgain() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) throws InterruptedException {
    final MyClass obj = new MyClass();
    final MyClass obj2 = new MyClass2();
    final MyClass obj3 = new MyClass3();
    final MyClass obj4 = new MyClass4();
    final MyClass obj5 = new MyClass5();
    for (int i = 0; i < 10_000; i++) {
      benchVMethod(obj);
      benchVMethod(obj2);
      benchVMethod(obj3);
      benchVMethod(obj4);
      benchVMethod(obj5);
    }
    Thread.sleep(1000);
  }

  public static void benchVMethod(MyClass obj) {
    obj.vmethod();
  }

  private static class MyClass {
    public void vmethod() {
      if (System.currentTimeMillis() == 0)
        System.out.println("call to MyClass.vmethod");
    }
  }

  private static class MyClass2 extends MyClass {
    @Override
    public void vmethod() {
      if (System.currentTimeMillis() == 0)
        System.out.println("call to MyClass2.vmethod");
    }
  }

  private static class MyClass3 extends MyClass {
    @Override
    public void vmethod() {
      if (System.currentTimeMillis() == 0)
        System.out.println("call to MyClass3.vmethod");
    }
  }

  private static class MyClass4 extends MyClass {
    @Override
    public void vmethod() {
      if (System.currentTimeMillis() == 0)
        System.out.println("call to MyClass4.vmethod");
    }
  }

  private static class MyClass5 extends MyClass {
    @Override
    public void vmethod() {
      if (System.currentTimeMillis() == 0)
        System.out.println("call to MyClass5.vmethod");
    }
  }
}
