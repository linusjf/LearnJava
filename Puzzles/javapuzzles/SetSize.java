package javapuzzles;

import java.util.Set;
import java.util.HashSet;

public enum SetSize {
  ;
  public static void main(String... args) {
    Set<Short> set = new HashSet<>();
    for (short i = 0; i < 10; i++) {
      set.add(i);
      set.remove(i - 1);
    }
    System.out.println(set.size());
    set = new HashSet<>();
    for (int i = 0; i < 10; i++) {
      set.add((short)i);
      set.remove(i - 1);
    }
    System.out.println(set.size());
    set = new HashSet<>();
    for (short i = 0; i < 10; i++) {
      set.add(i);
      set.remove(i - (short)1);
    }
    System.out.println(set.size());
    set = new HashSet<>();
    for (short i = 0; i < 10; i++) {
      set.add(i);
      set.remove((short)(i - 1));
    }
    System.out.println(set.size());
  }
}
