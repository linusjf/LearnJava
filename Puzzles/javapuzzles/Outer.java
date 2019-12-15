package javapuzzles;

@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
final class Outer {

  public static void main(String[] args) {
    Outer.Inner in = new Outer().new Inner();
    in.show();
    Outer outer = new Outer();
    outer.outerMethod();
  }

  void outerMethod() {
    int x = 98;
    System.out.println("inside outerMethod");

    // Inner class is local to outerMethod()
    abstract class AbstractInner {

      void innerMethod() {
        System.out.println("inside innerMethod");
        System.out.println("x= " + x);
      }
    }

    AbstractInner y = new AbstractInner() {};
    y.innerMethod();
  }

  // Simple nested inner class
  class Inner {

    public void show() {
      System.out.println("In a nested class method");
    }
  }
}
