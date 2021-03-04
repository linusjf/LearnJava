package javapuzzles;

import static java.lang.Math.abs;

public final class FloatingPoint {
  private FloatingPoint() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    loopAddFloat();
    loopAddDouble();
    nutty();
    finite();
  }

  private static void loopAddFloat() {
    float x = 0.1f;
    float y = 0;
    for (int i = 0; i < 10; i++)
      y += x;
    if (y == x)
      System.out.println("Algebra is truth.");
    else
      System.out.println("Not here.");
    System.out.println(1.0 - y);
    if (abs(1.0 - y) < 0.000001)
      System.out.println("Close enough for government work.");
    else
      System.out.println("Not even close.");
  }

  private static void loopAddDouble() {
    double x = 0.1f;
    double y = 0;
    for (int i = 0; i < 10; i++)
      y += x;
    if (y == x)
      System.out.println("Algebra is truth.");
    else
      System.out.println("Not here.");
    System.out.println(1.0 - y);
    if (abs(1.0 - y) < 0.000001)
      System.out.println("Close enough for government work.");
    else
      System.out.println("Not even close.");
  }

  private static void nutty() {
    double x = 1.25e8;
    double y = x + 7.5e-10;
    if (x == y)
      System.out.println("Am I nuts or what?");
  }

  private static void finite() {
    double a;
    int i;

    a = 0.2;
    a += 0.1;
    a -= 0.3;

    for (i = 0; a < 1.0; i++)
      a += a;

    System.out.printf("i=%d, a=%f\n", i, a);
  }
}
