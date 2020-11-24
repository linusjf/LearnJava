package jls;

public final class UnaryNumeric {

  private UnaryNumeric() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... ignored) {
    byte b = 2;
    // dimension expression promotion
    int[] a = new int[b];
    char c = '\u0001';
    // index expression promotion
    a[c] = 1;
    // unary - promotion
    a[0] = -c;
    System.out.println("a: " + a[0] + "," + a[1]);
    b = -1;
    // bitwise complement promotion
    int i = ~b;
    System.out.println("~0x" + Integer.toHexString(b) + "==0x"
                       + Integer.toHexString(i));
    // shift promotion (left operand)
    i = b << 4L;
    System.out.println("0x" + Integer.toHexString(b) + "<<4L==0x"
                       + Integer.toHexString(i));
  }
}
