package proxy.synchronicity;

/**
 * Describe class <code>BlackBoxProvider</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class BlackBoxProvider { // NOPMD

  private BlackBoxProvider() {}

  /**
   * Describe <code>getBlackBox</code> method here.
   *
   * @return an <code>IBlackBox</code> value
   */
  public static IBlackBox getBlackBox() {
    return SyncProxyWrapper.wrap(IBlackBox.class, new BlackBox());
  }
}
