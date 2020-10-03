package collections;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.SplitIterator;
import java.util.List;

public enum TestListIterator {
  ;

  public static void main(String... args) {
    LinkedList<Integer> ints = new LinkedList<>();
    ListIterator<Integer> iter = ints.listIterator(0);
    iter.add(100);
    iter.add(200);
    iter.add(300);
    System.out.println(ints);
    while (iter.hasNext()) {
      int val = iter.next();
      iter.remove();
      if (val == 100)
        iter.add(400);
    }
    System.out.println(ints);
  }
}
