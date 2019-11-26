package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ReverseCapture {
  ;

  public static void reverse(List<?> list) {
    rev(list);
  }

  private static <T> void rev(List<T> list) {
    List<T> tmp = new ArrayList<>(list);
    for (int i = 0; i < list.size(); i++)
      list.set(i, tmp.get(list.size() - i - 1));
  }

  public static void main(String... args) {
    List<?> objects = Arrays.asList("Hello", "Humble", "Bundle");
    reverse(objects);
    System.out.println(objects);
  }
}
