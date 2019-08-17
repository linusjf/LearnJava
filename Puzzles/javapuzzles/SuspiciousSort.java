package javapuzzles;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public enum SuspiciousSort {
  ;

  enum Order {
    ASCENDING,
    DESCENDING,
    CONSTANT,
    UNORDERED,
  }

  public static void main(String[] args) {
    Random rnd = new Random();
    Integer[] arr = new Integer[100];
    for (int i = 0; i < arr.length; i++) arr[i] = rnd.nextInt();
    Comparator<Integer> cmp =
        new Comparator<Integer>() {
          @Override
          public int compare(Integer i1, Integer i2) {
            return i2 - i1;
          }
        };
    Arrays.sort(arr, cmp);
    System.out.println(order(arr));
    collectionsMain(args);
    refactoredMain(args);
  }

  public static void collectionsMain(String... args) {
    Random rnd = new Random();
    Integer[] arr = new Integer[100];
    for (int i = 0; i < arr.length; i++) arr[i] = rnd.nextInt();
    Arrays.sort(arr, Collections.reverseOrder());
    System.out.println(order(arr));
  }

  public static void refactoredMain(String... args) {
    Random rnd = new Random();
    Integer[] arr = new Integer[100];
    for (int i = 0; i < arr.length; i++) arr[i] = rnd.nextInt();
    Comparator<Integer> cmp =
        new Comparator<Integer>() {
          @Override
          public int compare(Integer i1, Integer i2) {
            return i2 < i1 ? -1 : i2 > i1 ? 1 : 0;
          }
        };
    Arrays.sort(arr, cmp);
    System.out.println(order(arr));
  }

  static Order order(Integer... a) {
    boolean ascending = false;
    boolean descending = false;
    for (int i = 1; i < a.length; i++) {
      ascending |= a[i] > a[i - 1];
      descending |= a[i] < a[i - 1];
    }
    if (ascending && !descending) return Order.ASCENDING;
    if (descending && !ascending) return Order.DESCENDING;
    if (!ascending) return Order.CONSTANT; // All elements equal
    return Order.UNORDERED; // Array is not sorted
  }
}
