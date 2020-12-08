package jls;

@SuppressWarnings("all")
public final class EvaluationOrder {
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
