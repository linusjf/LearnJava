package hello;

/**
 * Describe class <code>Hello</code> here.
 */
public final class Hello {
  @SuppressWarnings("all")
  private Hello() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }
  
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String... args) {
    System.out.println("Hello world, on termux!");
  }
}
