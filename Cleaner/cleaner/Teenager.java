package cleaner;

/**
 * Describe class <code>Teenager</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class Teenager {
  private Teenager() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings({"checkstyle:magicnumber", "PMD.SystemPrintln"})
  public static void main(String[] args) {
    new Room(99);
    System.out.println("Peace out");
  }
}
