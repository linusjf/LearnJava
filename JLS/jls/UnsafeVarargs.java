package jls;

import java.util.Objects;

public final class UnsafeVarargs<T> {

  private UnsafeVarargs() {
    throw new IllegalStateException("Private constructor.");
  }

  public static <T> T[] unsafe(T... elements) {
    // unsafe! don't ever return a parameterized varargs array
    return elements;
  }

  public static <T> T[] broken(T seed) {
    // broken! This will be an Object[] no matter what T is
    return unsafe(seed, seed, seed);
  }

  public static String[] plant() {
    // ClassCastException
    return broken("seed");
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String... ignored) {
    System.out.println(Objects.toString(plant()));
  }
}
