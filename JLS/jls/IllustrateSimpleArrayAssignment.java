package jls;

@SuppressWarnings("all")
class ArrayReferenceThrow extends RuntimeException {}

@SuppressWarnings("all")
class IndexThrow extends RuntimeException {}

@SuppressWarnings("all")
class RightHandSideThrow extends RuntimeException {}

@SuppressWarnings("all")
public final class IllustrateSimpleArrayAssignment {
  static Object[] objects = {new Object(), new Object()};
  static Thread[] threads = {new Thread(), new Thread()};

  private IllustrateSimpleArrayAssignment() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  static Object[] arrayThrow() {
    throw new ArrayReferenceThrow();
  }

  static int indexThrow() {
    throw new IndexThrow();
  }

  static Thread rightThrow() {
    throw new RightHandSideThrow();
  }

  static String name(Object q) {
    String sq = q.getClass().getName();
    int k = sq.lastIndexOf('.');
    return (k < 0) ? sq : sq.substring(k + 1);
  }

  static void testFour(Object[] x, int j, Object y) {
    String sx = x == null ? "null" : name(x[0]) + "s";
    String sy = name(y);
    System.out.println();
    try {
      System.out.print(sx + "[throw]=throw => ");
      x[indexThrow()] = rightThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sx + "[throw]=" + sy + " => ");
      x[indexThrow()] = y;
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sx + "[" + j + "]=throw => ");
      x[j] = rightThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sx + "[" + j + "]=" + sy + " => ");
      x[j] = y;
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
  }

  public static void main(String[] args) {
    try {
      System.out.print("throw[throw]=throw => ");
      arrayThrow()[indexThrow()] = rightThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[throw]=Thread => ");
      arrayThrow()[indexThrow()] = new Thread();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[1]=throw => ");
      arrayThrow()[1] = rightThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[1]=Thread => ");
      arrayThrow()[1] = new Thread();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }

    testFour(null, 1, new StringBuffer());
    testFour(null, 9, new Thread());
    testFour(objects, 1, new StringBuffer());
    testFour(objects, 1, new Thread());
    testFour(objects, 9, new StringBuffer());
    testFour(objects, 9, new Thread());
    testFour(threads, 1, new StringBuffer());
    testFour(threads, 1, new Thread());
    testFour(threads, 9, new StringBuffer());
    testFour(threads, 9, new Thread());
  }
}
