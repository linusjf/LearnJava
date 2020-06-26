package collections;

import java.util.ArrayList;
import java.util.List;

public enum WildcardRestrictions {
  ;

  public static void main(String... args) {
    List<Number> nums = new ArrayList<>(10);
    List<? super Number> sink = nums;
    List<? extends Number> source = nums;
    for (int i = 0; i < 10; i++)
      sink.add(i);
    System.out.println(sink);
    double sum = 0;
    for (Number num: source)
      sum += num.doubleValue();
    System.out.println(sum);
  }
}
