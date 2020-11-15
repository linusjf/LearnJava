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
    List<T> list = Arrays.asList(elements);
    return list;
  }

  public static <T> List<T> broken(T seed) {
    // broken! This will be an Object[] no matter what T is
    List<T> plant = safe(seed, seed, seed);
    return plant;
  }

  public static Number[] plant() {
    // ClassCastException
    Number[] plants = broken(10).toArray(new Integer[0]);
    return plants;
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String... args) {
    System.out.println(Arrays.toString(plant()));
  }
}
