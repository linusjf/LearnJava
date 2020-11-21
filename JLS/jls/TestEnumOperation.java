package jls;

public final class TestEnumOperation {
  private TestEnumOperation() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    double x = Double.parseDouble(args[0]);
    double y = Double.parseDouble(args[1]);
    for (Operation op: Operation.values())
      System.out.println(x + " " + op + " " + y + " = " + op.eval(x, y));
  }

  enum Operation {
    PLUS {
      double eval(double x, double y) {
        return x + y;
      }
    },
    MINUS {
      double eval(double x, double y) {
        return x - y;
      }
    },
    TIMES {
      double eval(double x, double y) {
        return x * y;
      }
    },
    DIVIDED_BY {
      double eval(double x, double y) {
        return x / y;
      }
    };

    // Each constant supports an arithmetic operation
    abstract double eval(double x, double y);
  }
}
