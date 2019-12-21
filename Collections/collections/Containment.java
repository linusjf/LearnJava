package collections;

import java.util.List;
import static java.util.Arrays.*;

public enum Containment {
  ;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... args) {
    Object obj = "one";
    List<Object> objs = asList("one", 2, Math.PI, 4);
    List<Integer> ints = asList(2, 4);
    assert objs.contains(obj);
    assert objs.containsAll(ints);
    assert !ints.contains(obj);
    assert !ints.containsAll(objs);

    obj = 1;
    objs = asList(1, 3);
    ints = asList(1, 2, 3, 4);
    assert ints.contains(obj);
    assert ints.containsAll(objs);
  }
}
