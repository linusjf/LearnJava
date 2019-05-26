package cleaner;

/**
 * Describe class <code>Adult</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Adult {

  private Adult() {
    throw new 
      IllegalStateException("Private constructor.");
  }

  @SuppressWarnings("checkstyle:magicnumber")
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {

    try (Room myRoom = new Room(7)) {

      System.out.println("Goodbye");
    }
  }
}
