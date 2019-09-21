package javapuzzles;

public class Foo {
  private final int i;

  public Foo(int i) {
    this.i = i;
  }

  public void method(Foo this) {
    System.out.println(this.i);
  }
}
