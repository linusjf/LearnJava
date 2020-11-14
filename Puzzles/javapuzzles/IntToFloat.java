package javapuzzles;

public final class IntToFloat {
  private IntToFloat() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    int big = 1234567890;
    float approx = big;
    System.out.println(big - (int)approx);
  }
}
