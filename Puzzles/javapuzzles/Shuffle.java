package javapuzzles;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public enum Shuffle {
  ;
  private static Random rnd = new Random();
  private static Map<Integer, Integer> map = new HashMap<>();

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    Integer[] nums = new Integer[100];
    for (int i = 0; i < 100; i++) nums[i] = rnd.nextInt(100);
    shuffle((Object) nums);
  }

  public static void shuffle(Object... a) {
    rnd = new Random(1);
    for (int i = 0; i < a.length; i++) {
      int shuffleTo = rnd.nextInt(a.length);
      swap(a, i, shuffleTo);
      storeInMap(i, shuffleTo);
    }
    dumpMap();
    map.clear();
    rnd = new Random(1);
    for (int i = 0; i < a.length; i++) {
      int shuffleTo = i + rnd.nextInt(a.length - i);
      swap(a, i, shuffleTo);
      storeInMap(i, shuffleTo);
    }
    dumpMap();
  }

  private static void storeInMap(int to, int from) {
    Integer count = map.get(to);
    if (count == null) map.put(to, 1);
    else map.put(to, ++count);

    Integer counter = map.get(from);
    if (counter == null) map.put(from, 1);
    else map.put(from, ++counter);
  }

  private static void dumpMap() {
    Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
    int min = 0;
    int max = 0;
    for (Map.Entry<Integer, Integer> entry : entries) {
      System.out.printf(" (%d : %d) ", entry.getKey(), entry.getValue());
      if (entry.getValue() > max) max = entry.getValue();
      if (entry.getValue() < min) min = entry.getValue();
    }
    System.out.printf("%nRange: %d - %d %n", min, max);
  }

  private static void swap(Object[] a, int i, int j) {
    Object tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }
}
