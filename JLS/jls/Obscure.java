package jls;

public final class Obscure {
  static int jls;

  private Obscure() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {

    System.out.println(jls);
  }
}
