package hello;

/** Describe class <code>Hello</code> here. */
public enum Hello {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] args) {
    System.out.println("Hello world, on termux!");
  }
}
