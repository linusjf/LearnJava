package proxy.synchronicity;

public class BlackBox implements IBlackBox {

  @Override
  public void methodA() {
     System.out.println("Into method A...");
  }

  @Override
  public void methodB() {
     System.out.println("Into method B...");
  }
}
