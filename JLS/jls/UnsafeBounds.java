package jls;

import java.util.Arrays;
import java.util.List;

public final class UnsafeBounds<T extends Number> {

  private UnsafeBounds() {
    throw new IllegalStateException("Private constructor.");
  }

  public static <T> T[] unsafe(T... elements) {
    // unsafe! don't ever return a parameterized varargs array
    return elements;
  }

  public static <T> List<T> safe(T... elements) {
    return Arrays.asList(elements);
  }

  public static <T> List<T> broken(T seed) {
    return safe(seed, seed, seed);
  }

  public static Number[] plant() {
    return broken(10).toArray(new Integer[0]);
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String... ignored) {
    System.out.println(Arrays.toString(plant()));
  }
}
