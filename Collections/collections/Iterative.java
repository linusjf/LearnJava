package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public enum Iterative {
  ;

  public static void removeNegative(Iterable <Double> v) {
    for (Iterator<Double> it = v.iterator(); it.hasNext();) {
      if (it.next() < 0)
        it.remove();
    }
  }

  public static double dot(List<Double> u, List<Double> v) {
    int usize = u.size();
    int vsize = v.size();
    if (usize != vsize)
      throw new IllegalArgumentException("different sizes: "
          + usize + " <> " + vsize);
    double d = 0;
    Iterator<Double> uiterator = u.iterator();
    Iterator<Double> viterator = v.iterator();
    while (uiterator.hasNext()) {
      assert uiterator.hasNext() && viterator.hasNext();
      d += uiterator.next() * viterator.next();
    }
    assert !uiterator.hasNext() && !viterator.hasNext();
    return d;
  }

  public static void main(String... args) {
    List<Double> doubles =
        new ArrayList<>(Arrays.asList(2.2d, 3.4d, 6.78d, 2.45d, -3.4d, -2.56d, 4.23d, -5.6d));
    removeNegative(doubles);
    List<Double> doubles2 =
        new ArrayList<>(Arrays.asList(2.2d, 3.4d, 6.78d, 2.45d, -3.4d, -2.56d, 4.23d, -5.6d));
    removeNegative(doubles2);
    assert doubles.equals(doubles2);
    double dotProduct = dot(doubles, doubles2);
    System.out.println("dotProduct = " + dotProduct);
  }
}
