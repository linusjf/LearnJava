package jls;

@SuppressWarnings("PMD")
public final class TestSuperStatic {

  private TestSuperStatic() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String[] args) {
    System.out.println(Sub.taxi);
  }

  static class Super {
    // static variable declaration
    static int taxi = 1729;
  }

  static class Sub extends Super {
    static {
      System.out.print("Sub ");
    }
  }
}
