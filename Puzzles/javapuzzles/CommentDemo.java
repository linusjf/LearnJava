package javapuzzles;

public final class CommentDemo {
  public static void main(String[] args) {
    for (int i = 0; i < 5; /* exits when i reaches to 5 */ i++) {
      System.out.print(i + " ");
    }
    String commS = "/* It looks like a comment but It is treated as string */";
    String commS1 = "// It too look like a comment but It is treated as string";
    System.out.println(commS);
    System.out.println(commS1);
    double pi = Math.PI;
    /* multiplies pi by 4 \u002a\u002f
    // above comment is lexically equivalent to the following
    /* multiplies pi by 4 */
    System.out.println(pi * 4);  // \u002A is Unicode of *
  }

  @SuppressWarnings("all")
  private CommentDemo() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }
}
