package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public enum Iterative {
  ;

  public static void removeNegative(List<Double> v) {
    for (Iterator<Double> it = v.iterator(); it.hasNext();) {
      if (it.next() < 0)
        it.remove();
    }
  }

  public static double dot(List<Double> u, List<Double> v) {
    if (u.size() != v.size())
      throw new IllegalArgumentException("different sizes");
    double d = 0;
    Iterator<Double> uIt = u.iterator();
    Iterator<Double> vIt = v.iterator();
    while (uIt.hasNext()) {
      assert uIt.hasNext() && vIt.hasNext();
      d += uIt.next() * vIt.next();
    }
    assert !uIt.hasNext() && !vIt.hasNext();
    return d;
  }

  public static void main(String... args) {
    List<Double> doubles = new ArrayList<>(
        Arrays.asList(2.2d, 3.4d, 6.78d, 2.45d, -3.4d, -2.56d, 4.23d, -5.6d));
    removeNegative(doubles);
    List<Double> doubles2 = new ArrayList<>(
        Arrays.asList(2.2d, 3.4d, 6.78d, 2.45d, -3.4d, -2.56d, 4.23d, -5.6d));
    removeNegative(doubles2);
    assert doubles.equals(doubles2);
    double dotProduct = dot(doubles, doubles2);
    System.out.println("dotProduct = " + dotProduct);
  }
}
