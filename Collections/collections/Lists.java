package collections;

import java.util.ArrayList;
import java.util.List;

public enum Lists {
  ;

  @SafeVarargs
  public static <T> List<T> toList(T... arr) {
    List<T> list = new ArrayList<>();
    for (T elt : arr) list.add(elt);
    return list;
  }

  @SafeVarargs
  public static <T> void addAll(List<T> list, T... arr) {
    for (T elt : arr) list.add(elt);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... args) {
    List<Integer> ints = Lists.toList(1, 2, 3);
    System.out.println(ints);
    List<String> words = Lists.toList("hello", "world");
    System.out.println(words);
    ints = new ArrayList<>();
    Lists.addAll(ints, 1, 2);
    Lists.addAll(ints, new Integer[] {3, 4});
    assert ints.toString().equals("[1, 2, 3, 4]");
    ints = Lists.toList();
    System.out.println(ints);
    List<Object> objs = Lists.toList(1, "two");
    System.out.println(objs);
  }
}
