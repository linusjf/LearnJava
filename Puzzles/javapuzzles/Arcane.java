package javapuzzles;

@SuppressWarnings("PMD")
public enum Arcane {
  ;

  public static void main(String[] args) {
    try {
      // If you have nothing nice to say, say nothing
    } catch (Exception e) {
      System.out.println("This can’t happen");
    }
    Type3 t3 = new Arcane3();
    t3.f();
  }
}

@SuppressWarnings({"checkstyle:onetoplevelclass", "PMD"})
interface Type1 {
  void f() throws CloneNotSupportedException;
}

@SuppressWarnings({"checkstyle:onetoplevelclass", "PMD"})
interface Type2 {
  void f() throws InterruptedException;
}

@SuppressWarnings({"checkstyle:onetoplevelclass", "PMD"})
interface Type3 extends Type1, Type2 {}

@SuppressWarnings({"checkstyle:onetoplevelclass", "PMD"})
class Arcane3 implements Type3 {
  public void f() {
    System.out.println("Hello world");
  }
}
