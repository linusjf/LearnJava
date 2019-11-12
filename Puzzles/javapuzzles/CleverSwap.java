package javapuzzles;

@SuppressWarnings("PMD")
public enum CleverSwap {
  ;

  @SuppressWarnings("checkstyle:innerassignment")
  public static void main(String[] args) {
    // (0x7c0)
    int x = 1984;

    // (0x7d1)
    int y = 2001;
    x ^= y ^= x ^= y;
    System.out.println("x = " + x + "; y = " + y);
    x = 1984;
    y = 2001;
    x = x ^ y;
    System.out.println("x = " + x + "; y = " + y);
    y = y ^ x;
    System.out.println("x = " + x + "; y = " + y);
    x = y ^ x;
    System.out.println("x = " + x + "; y = " + y);
    x = 1984;
    y = 2001;
    System.out.println((x ^ y ^ x) == y);
    x = x + y;
    System.out.println("x = " + x + "; y = " + y);
    y = x - y;
    System.out.println("x = " + x + "; y = " + y);
    x = x - y;
    System.out.println("x = " + x + "; y = " + y);
    x = 1984;
    y = 2001;
    x = y - x;
    System.out.println("x = " + x + "; y = " + y);
    y = y - x;
    System.out.println("x = " + x + "; y = " + y);
    x = x + y;
    System.out.println("x = " + x + "; y = " + y);
    x = 1984;
    y = 2001;
    y = (x ^= y ^= x) ^ y;
    System.out.println("x = " + x + "; y = " + y);
  }
}
