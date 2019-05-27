package cleaner;

/**
 * Describe class <code>Adult</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class Adult {

  private Adult() {
    throw new 
      IllegalStateException("Private constructor.");
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings({"checkstyle:magicnumber","checkstyle:illegaltoken"})
  public static void main(String[] args) {

    try (Room myRoom = new Room(7)) {
     assert myRoom != null;

      System.out.println("Goodbye");
    }
  }
}
