package javapuzzles;

import java.util.Arrays;

public enum BananaBread {
  ;

  public static void main(String[] args) {
    Integer[] array = {3, 1, 4, 1, 5, 9};
    // clang-format off
    Arrays.sort(array, (i1, i2) -> { 
      return i1 < i2 ? -1 : i2 > i1 ? 1 : 0; 
    });
    // clang-format on
    System.out.println(Arrays.toString(array));
  }
}
