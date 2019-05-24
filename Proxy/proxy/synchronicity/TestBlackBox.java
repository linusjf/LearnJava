package proxy.synchronicity;

public enum TestBlackBox {
  ;

  public static void main(String[] args) {

    IBlackBox bb = BlackBoxProvider.getBlackBox();
    bb.methodA();
    bb.methodB();
  }
}
