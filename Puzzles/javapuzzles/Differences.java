package javapuzzles;

import java.util.HashSet;
import java.util.Set;

public enum Differences {
  ;
  @SuppressWarnings("PMD.AvoidUsingOctalValues")
  public static void main(String[] args) {
    int[] vals = { 789, 678, 567, 456, 345, 234, 123, 012 };
    Set<Integer> diffs = new HashSet<>();
    for (int i = 0; i < vals.length; i++)
      for (int j = i; j < vals.length; j++)
        diffs.add(vals[i] - vals[j]);
    System.out.println(diffs.size());
    altMain(args);
  }
  
  public static void altMain(String... args) {
    int[] vals = { 789, 678, 567, 456, 345, 234, 123, 12 };
    Set<Integer> diffs = new HashSet<>();
    for (int i = 0; i < vals.length; i++)
      for (int j = i; j < vals.length; j++)
        diffs.add(vals[i] - vals[j]);
    System.out.println(diffs.size());
  }
}
