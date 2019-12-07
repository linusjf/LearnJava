package javapuzzles;

@SuppressWarnings("PMD.ShortClassName")
public final class Odd {

  private Odd() {
    throw new IllegalStateException("Private constructor");
  }

  public static boolean isOddModulo(int i) {
    return i % 2 == 1;
  }

  public static boolean isOddAnd(int i) {
    return (i & 1) != 0;
  }

  public static void main(String... args) {
    System.out.println(isOddModulo(10) == isOddAnd(10));

    System.out.println(isOddModulo(-5) == isOddAnd(-5));

    System.out.println(isOddModulo(-10) == isOddAnd(-10));

    System.out.printf("-10 %% 2 = %d%n", -10 % 2);
    System.out.printf("-15 %% 2 = %d%n", -15 % 2);
  }
}
