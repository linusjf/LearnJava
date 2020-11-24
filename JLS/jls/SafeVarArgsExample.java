package jls;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("PMD.SystemPrintln")
public class SafeVarArgsExample {
  // We are not using @SafeVarargs annotation - Java 9
  @SuppressWarnings({"varargs", "unchecked", "rawtypes"})
  private void print(List... names) {
    for (List<String> name: (List<String>[])names)
      System.out.println(name);
  }

  // We are using @SafeVarargs annotation - Java 9
  @SafeVarargs
  @SuppressWarnings({"varargs", "unchecked", "rawtypes"})
  private void printList(List... names) {
    for (List<String> name: (List<String>[])names)
      System.out.println(name);
  }

  @SafeVarargs
  @SuppressWarnings({"varargs", "unchecked"})
  private void printSafeList(List<String>... names) {
    for (List<String> name: names)
      System.out.println(name);
  }

  public static void main(String... ignored) {
    final SafeVarArgsExample obj = new SafeVarArgsExample();
    List<String> list = new ArrayList<>();
    list.add("Kevin");
    list.add("Rick");
    list.add("Megan");
    obj.print(list);
    obj.printList(list);
    obj.printSafeList(list);
  }
}
