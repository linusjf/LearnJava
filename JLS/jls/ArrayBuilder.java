package jls;

import java.util.Arrays;
import java.util.List;

public final class ArrayBuilder {

  private ArrayBuilder() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings({"unchecked", "varargs"})
  public static <T> void addToList(List<T> listArg, T... elements) {
    for (T x: elements)
      listArg.add(x);
  }

  @SuppressWarnings({"unchecked", "varargs"})
  public static <T> void addToList2(List<T> listArg, T... elements) {
    for (T x: elements)
      listArg.add(x);
  }

  @SafeVarargs
  @SuppressWarnings({"unchecked", "varargs"})
  public static <T> void addToList3(List<T> listArg, T... elements) {
    for (T x: elements)
      listArg.add(x);
  }

  @SuppressWarnings({"PMD.SystemPrintln", "unchecked", "varargs"})
  public static void faultyMethod(List<String>... l) {
    // Valid
    Object[] objectArray = l;
    objectArray[0] = Arrays.asList(Integer.valueOf(42));
    // ClassCastException thrown here
    String s = l[0].get(0);
    System.out.println(s);
  }
}
