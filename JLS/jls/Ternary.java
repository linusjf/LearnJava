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

  static Class<? super Integer> chooseClass(boolean b,
                                            Class<Integer> c1,
                                            Class<Number> c2) {
    return b ? c1 : c2;
  }

  public static void main(String... args) {
    System.out.println(choose(true, Integer.MAX_VALUE, Long.MAX_VALUE));
    System.out.println(choose(false, Integer.MAX_VALUE, Long.MAX_VALUE));
    System.out.println(choose(true, Integer.class, Number.class));
    System.out.println(choose(false, Integer.class, Number.class));
    System.out.println(chooseClass(true, Integer.class, Number.class));
    System.out.println(chooseClass(false, Integer.class, Number.class));
  }
}
