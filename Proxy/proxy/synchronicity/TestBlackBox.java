package proxy.synchronicity;

public enum TestBlackBox {
  ;
  /**
   * Main program.
   *
   * @param args array of String arguments.
   */
  public static void main(String[] args) {
    IBlackBox bb = BlackBoxProvider.getBlackBox();
    testMethods(bb);
  }

  private static void testMethods(IBlackBox bb) {
    bb.methodA();
    bb.methodB();
  }
}
