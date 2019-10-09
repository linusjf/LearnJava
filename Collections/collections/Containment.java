package collections;

import java.util.Arrays;
import java.util.List;

public enum Containment {
  ;
  public static void main(String... args) {
    Object obj = "one";
    List<Object> objs = Arrays.<Object>asList("one", 2, 3.14, 4);
    List<Integer> ints = Arrays.asList(2, 4);
    assert objs.contains(obj);
    assert objs.containsAll(ints);
    assert !ints.contains(obj);
    assert !ints.containsAll(objs);

    obj = 1;
    objs = Arrays.<Object>asList(1, 3);
    ints = Arrays.asList(1, 2, 3, 4);
    assert ints.contains(obj);
    assert ints.containsAll(objs);
  }
}
