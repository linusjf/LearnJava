package pmdtests;

public class Foo {
  private final int i;

  public Foo(int i) {
    this.i = i;
  }

  public void meth() {
    System.out.println(this.i);
  }

  public void method(Foo this) {
    System.out.println(this.i);
  }

  public void method(Foo this, String... args) {
    System.out.println(this.i);
    for (String arg: args)
      System.out.println(arg);
  }

  private void bar(String howdy) {
    // howdy is not used
  }
}
