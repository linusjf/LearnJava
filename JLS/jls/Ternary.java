package jls;

@SuppressWarnings("all")
public final class Ternary {
  private Ternary() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  static <T, S> Object choose(boolean b, T first, S second) {
    return b ? first : second;
  }

  public static void main(String... args) {
    System.out.println(choose(true, Integer.MAX_VALUE, Long.MAX_VALUE));
    System.out.println(choose(false, Integer.MAX_VALUE, Long.MAX_VALUE));
  }
}
