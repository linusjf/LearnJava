package jls;

@SuppressWarnings("PMD")
public final class Narrowing {
  private Narrowing() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... ignored) {
    narrowPrimitives();
    narrowPrimitivesLossy();
  }

  public static void narrowPrimitives() {
    float fmin = Float.NEGATIVE_INFINITY;
    float fmax = Float.POSITIVE_INFINITY;
    System.out.println("long: " + (long)fmin + ".." + (long)fmax);
    System.out.println("int: " + (int)fmin + ".." + (int)fmax);
    System.out.println("short: " + (short)fmin + ".." + (short)fmax);
    System.out.println("char: " + (int)(char)fmin + ".." + (int)(char)fmax);
    System.out.println("byte: " + (byte)fmin + ".." + (byte)fmax);
  }

  public static void narrowPrimitivesLossy() {
    // A narrowing of int to short loses high bits:
    System.out.println("(short)0x12345678==0x"
                       + Integer.toHexString((short)0x12345678));
    // An int value too big for byte changes sign and magnitude:
    System.out.println("(byte)255==" + (byte)255);
    // A float value too big to fit gives largest int value:
    System.out.println("(int)1e20f==" + (int)1e20f);
    // A NaN converted to int yields zero:
    System.out.println("(int)NaN==" + (int)Float.NaN);
    // A double value too large for float yields infinity:
    System.out.println("(float)-1e100==" + (float)-1e100);
    // A double value too small for float underflows to zero:
    System.out.println("(float)1e-50==" + (float)1e-50);
  }
}
