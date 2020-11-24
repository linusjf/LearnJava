package jls;

public final class Obscure {
  static int jls;

  private Obscure() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... ignored) {

    System.out.println(jls);
  }
}
