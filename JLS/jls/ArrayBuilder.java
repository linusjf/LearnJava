package jls;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("PMD.SystemPrintln")
public final class ArrayBuilder {

  private ArrayBuilder() {
    throw new IllegalStateException("Private constructor");
  }

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
  public static <T> void addToList3(List<T> listArg, T... elements) {
    for (T x: elements)
      listArg.add(x);
  }

  public static void faultyMethod(List<String>... l) {
    // Valid
    Object[] objectArray = l;
    objectArray[0] = Arrays.asList(Integer.valueOf(42));
    // ClassCastException thrown here
    String s = l[0].get(0);
    System.out.println(s);
  }
}
