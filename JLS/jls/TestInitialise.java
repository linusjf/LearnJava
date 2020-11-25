package jls;

public final class TestInitialise {
  private TestInitialise() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String[] ignored) {
    One o = null;
    Two t = new Two();
    System.out.println((Object)o == (Object)t);
  }

  static class Super {
    static {
      System.out.print("Super ");
    }
  }

  static class One {
    static {
      System.out.print("One ");
    }
  }

  static class Two extends Super {
    static {
      System.out.print("Two ");
    }
  }
}
