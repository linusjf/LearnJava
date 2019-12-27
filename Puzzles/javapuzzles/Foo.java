package javapuzzles;

// https://twitter.com/heinzkabutz/status/1175283793592233985
@SuppressWarnings("PMD.ShortClassName")
public class Foo {
  private final int i;

  public Foo(int i) {
    this.i = i;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void meth() {
    System.out.println(this.i);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void method(Foo this) {
    System.out.println(this.i);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void method(Foo this, String... args) {
    System.out.println(this.i);
    for (String arg: args)
      System.out.println(arg);
  }

  public static void main(String... args) {
    Foo foo = new Foo(42);
    foo.method();
    Bar bar = new Bar(42);
    bar.method();
    bar.method("hello", "good morning", "aloha");
  }
}

@SuppressWarnings({"checkstyle:onetoplevelclass", "PMD.ShortClassName"})
class Bar extends Foo {
  Bar(int i) {
    super(i);
  }

  @Override
  public void method() {
    // empty method
  }

  @Override
  public void method(String... args) {
    // empty method
  }
}
