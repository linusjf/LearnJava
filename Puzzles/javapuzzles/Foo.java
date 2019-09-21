package javapuzzles;

// https://twitter.com/heinzkabutz/status/1175283793592233985
public class Foo {
  private final int i;

  public Foo(int i) {
    this.i = i;
  }

  public void method(Foo this) {
    System.out.println(this.i);
  }

  public static void main(String... args) {
    Foo foo = new Foo(42);
    foo.method();
  }
}

class Bar extends Foo {
  public Bar(int i) {
    super(i);
  }

  public void method() {
    // empty method
  }
}
