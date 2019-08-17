package javapuzzles;

import java.util.Arrays;
import java.util.Comparator;

public enum BananaBread {
  ;

  public static void main(String[] args) {
    Integer[] array = {3, 1, 4, 1, 5, 9};
    Arrays.sort(array, new Comparator<Integer>() {
      @Override
      public int compare(Integer i1, Integer i2) {
        return i1 < i2 ? -1 : (i2 > i1 ? 1 : 0);
      }
    });
    System.out.println(Arrays.toString(array));
  }
}
