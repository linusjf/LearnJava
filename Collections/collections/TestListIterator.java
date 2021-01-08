package collections;

import java.util.LinkedList;
import java.util.ListIterator;

public enum TestListIterator {
  ;

  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
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
    iter = ints.listIterator(0);
    while (iter.hasNext()) {
      int val = iter.next();
      iter.remove();
      iter.add(val + 1);
      iter.add(val - 1);
    }
    System.out.println(ints);
    testAddIteratedCheck();
  }

  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  static void testAddIteratedCheck() {
    LinkedList<Integer> list = new LinkedList<>();

    list.add(10);
    list.add(20);
    list.add(1);
    ListIterator<Integer> iter = list.listIterator();
    while (iter.hasNext()) {
      System.out.println("iter : " + iter);
      int val = iter.next();
      System.out.println("iter next : " + iter);
      System.out.println(list);
      iter.remove();
      System.out.println("iter remove : " + iter);
      System.out.println(list);
      iter.add(val + 1);
      System.out.println("iter first add : " + iter);
      System.out.println(list);
      if (iter.hasPrevious()) {
        val = iter.previous();
        System.out.println("iter previous : " + iter);
        System.out.println(list);
      }
      iter.add(val + 1);
      System.out.println("iter second add : " + iter);
      System.out.println(list);
      if (list.size() >= 100)
        break;
    }
    System.out.println(list);
  }
}
