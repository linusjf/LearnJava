package javapuzzles;

import java.util.List;
import java.util.Arrays;

public enum InSum {
  ;
  public static int sum(List<Integer> ints) {
    int s = 0;
    for (int n: ints)
      s += n;
    return s;
  }

  public static Integer sumInteger(List<Integer> ints) {
    Integer s = 0;
    for (Integer n: ints) {
      s += n;
    }
    return s;
  }

  public static void main(String... args) {
    List<Integer> bigs = Arrays.asList(100, 200, 300);
    System.out.println(sumInteger(bigs) == sum(bigs));
    System.out.println(sumInteger(bigs) == sumInteger(bigs)); 
    assert sumInteger(bigs) == sum(bigs);
    assert sumInteger(bigs) != sumInteger(bigs);  // not recommended
  }
}
