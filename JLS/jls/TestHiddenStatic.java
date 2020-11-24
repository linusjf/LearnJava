package jls;

public final class TestHiddenStatic {

  private TestHiddenStatic() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] ignored) {
    Super s = new Sub();
    System.out.println(s.greeting() + ", " + s.name());
  }

  static class Super {
    static String greeting() {
      return "Goodnight";
    }

    String name() {
      return "Richard";
    }
  }

  static class Sub extends Super {
    static String greeting() {
      return "Hello";
    }

    @Override
    String name() {
      return "Dick";
    }
  }
}
