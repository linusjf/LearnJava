package javapuzzles;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("PMD.SystemPrintln")
public enum Differences {
  ;

  @SuppressWarnings({"PMD.AvoidUsingOctalValues", "PMD.LawOfDemeter"})
  public static void main(String[] unused) {
    int[] vals = {789, 678, 567, 456, 345, 234, 123, 012};
    Set<Integer> diffs = new HashSet<>();
    for (int i = 0; i < vals.length; i++)
      for (int j = i; j < vals.length; j++)
        diffs.add(vals[i] - vals[j]);
    System.out.println(diffs.size());
    altMain(args);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void altMain(String... unused) {
    int[] vals = {789, 678, 567, 456, 345, 234, 123, 12};
    Set<Integer> diffs = new HashSet<>();
    for (int i = 0; i < vals.length; i++)
      for (int j = i; j < vals.length; j++)
        diffs.add(vals[i] - vals[j]);
    System.out.println(diffs.size());
  }
}
