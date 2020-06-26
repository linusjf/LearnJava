package collections;

import java.util.SortedSet;
import java.util.TreeSet;

public enum Symposium {
  ;

  public static void main(String... args) {
    SortedSet<Integer> treeSet = new TreeSet<>();
    treeSet.add(6);
    treeSet.add(4);
    treeSet.add(5);
    treeSet.add(4);
    treeSet.add(1);

    for (Integer temp: treeSet)
      System.out.println(temp);
  }
}
