package jls;

@SuppressWarnings("all")
public final class Remainder {
  private Remainder() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String[] args) {
    // 2
    int a = 5 % 3;
    // 1
    int b = 5 / 3;
    System.out.println("5%3 produces " + a + " (note that 5/3 produces " + b
                       + ")");
    // 2
    int c = 5 % (-3);
    // -1
    int d = 5 / (-3);
    System.out.println("5%(-3) produces " + c + " (note that 5/(-3) produces "
                       + d + ")");
    // -2
    int e = (-5) % 3;
    // -1
    int f = (-5) / 3;
    System.out.println("(-5)%3 produces " + e + " (note that (-5)/3 produces "
                       + f + ")");
    // -2
    int g = (-5) % (-3);
    // 1
    int h = (-5) / (-3);
    System.out.println("(-5)%(-3) produces " + g
                       + " (note that (-5)/(-3) produces " + h + ")");
    double al = 5.0 % 3.0;
    // 2.0
    System.out.println("5.0%3.0 produces " + al);
    double bl = 5.0 % (-3.0);
    // 2.0
    System.out.println("5.0%(-3.0) produces " + bl);
    double cl = (-5.0) % 3.0;
    // -2.0
    System.out.println("(-5.0)%3.0 produces " + cl);
    double dl = (-5.0) % (-3.0);
    // -2.0
    System.out.println("(-5.0)%(-3.0) produces " + dl);
  }
}
