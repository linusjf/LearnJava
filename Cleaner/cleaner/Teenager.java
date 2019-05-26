package cleaner;

/**
 * Describe class <code>Teenager</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Teenager {
  
  private Teenager() {
    throw new 
      IllegalStateException("Private constructor.");
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings("checkstyle:magicnumber")
  public static void main(String[] args) {
    new Room(99);
    System.out.println("Peace out");
  }
}
