package javapuzzles;

import java.util.Arrays;
import java.util.List;

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
    assert sumInteger(bigs) != sumInteger(bigs);  
    // not recommended

    // the effect of caching demonstrated
    List<Integer> smalls = Arrays.asList(1, 2, 3);
    System.out.println(sumInteger(smalls) == sum(smalls));
    System.out.println(sumInteger(smalls) == sumInteger(smalls));
    assert sumInteger(smalls) == sum(smalls);
    assert sumInteger(smalls) == sumInteger(smalls);
  }
}
