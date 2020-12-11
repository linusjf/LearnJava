package jls;

@SuppressWarnings("all")
public final class IllustrateCompoundArrayAssignment {
  static String[] strings = {"Simon", "Garfunkel"};
  static double[] doubles = {Math.E, Math.PI};

  private IllustrateCompoundArrayAssignment() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  static String[] stringsThrow() {
    throw new ArrayReferenceThrow();
  }

  static double[] doublesThrow() {
    throw new ArrayReferenceThrow();
  }

  static int indexThrow() {
    throw new IndexThrow();
  }

  static String stringThrow() {
    throw new RightHandSideThrow();
  }

  static double doubleThrow() {
    throw new RightHandSideThrow();
  }

  static String name(Object q) {
    String sq = q.getClass().getName();
    int k = sq.lastIndexOf('.');
    return (k < 0) ? sq : sq.substring(k + 1);
  }

  static void testEight(String[] x, double[] z, int j) {
    String sx = (x == null) ? "null" : "Strings";
    String sz = (z == null) ? "null" : "doubles";
    System.out.println();
    try {
      System.out.print(sx + "[throw]+=throw => ");
      x[indexThrow()] += stringThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sz + "[throw]+=throw => ");
      z[indexThrow()] += doubleThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sx + "[throw]+=\"heh\" => ");
      x[indexThrow()] += "heh";
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sz + "[throw]+=12345 => ");
      z[indexThrow()] += 12345;
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sx + "[" + j + "]+=throw => ");
      x[j] += stringThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sz + "[" + j + "]+=throw => ");
      z[j] += doubleThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sx + "[" + j + "]+=\"heh\" => ");
      x[j] += "heh";
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print(sz + "[" + j + "]+=12345 => ");
      z[j] += 12345;
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
  }

  public static void main(String[] args) {
    try {
      System.out.print("throw[throw]+=throw => ");
      stringsThrow()[indexThrow()] += stringThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[throw]+=throw => ");
      doublesThrow()[indexThrow()] += doubleThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[throw]+=\"heh\" => ");
      stringsThrow()[indexThrow()] += "heh";
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[throw]+=12345 => ");
      doublesThrow()[indexThrow()] += 12345;
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[1]+=throw => ");
      stringsThrow()[1] += stringThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[1]+=throw => ");
      doublesThrow()[1] += doubleThrow();
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[1]+=\"heh\" => ");
      stringsThrow()[1] += "heh";
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    try {
      System.out.print("throw[1]+=12345 => ");
      doublesThrow()[1] += 12345;
      System.out.println("Okay!");
    } catch (Throwable e) {
      System.out.println(name(e));
    }
    testEight(null, null, 1);
    testEight(null, null, 9);
    testEight(strings, doubles, 1);
    testEight(strings, doubles, 9);
  }
}
