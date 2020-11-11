package jls;

public class TestHiddenClass {

  static class Hidden {}

  static class Inner {
    class Hidden {}
  }

  static class SecondInner {
    static class Hidden {}
  }
}
