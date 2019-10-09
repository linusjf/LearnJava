package proxy.synchronicity;

/**
 * Describe class <code>BlackBox</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
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
