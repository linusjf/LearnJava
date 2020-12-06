package jls;

@SuppressWarnings("all")
public final class SuperDefault {
  public SuperDefault() {
    throw new UnsupportedOperationException();
  }

  public static void main(String... args) {
    SubClass sc = new SubClass();
    sc.tweak();
    sc.tweakAgain();
  }
}

@SuppressWarnings("all")
interface Superinterface {
  default void foo() {
    System.out.println("Hi");
  }
}

@SuppressWarnings("all")
class SubClass implements Superinterface {
  public void foo() {
    throw new UnsupportedOperationException();
  }

  // Gets the 'println' behavior
  void tweak() {
    Superinterface.super.foo();
  }
  
  void tweakAgain() {
    Superinterface si = (Superinterface)this;
    si.foo();
  }
}

