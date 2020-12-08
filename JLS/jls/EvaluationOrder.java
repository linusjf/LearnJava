package jls;

public final class EvaluationOrder {
  @SuppressWarnings("all")
  private EvaluationOrder() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String[] args) {
    String s = "one";
    if (s.startsWith(s = "two"))
      System.out.println("oops");
  }
}
