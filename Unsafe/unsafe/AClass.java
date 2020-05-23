package unsafe;

import sun.misc.Unsafe; // NOPMD

public class AClass {
  // not initialized value
  private final long a;

  public AClass() {
    // initialization
    this.a = 1;
  }

  public long getValue() {
    return this.a;
  }

  @Override
  public String toString() {
    return String.valueOf(a);
  }

  @SuppressWarnings({"PMD.SystemPrintln", "PMD.LawOfDemeter"})
  public static void main(String... args) {
    try {
      Unsafe unsafe = Counter.getUnsafe();
      // constructor
      AClass o1 = new AClass();
      // prints 1
      System.out.println(o1);

      // reflection
      AClass o2 = AClass.class.newInstance();
      // prints 1
      System.out.println(o2);

      // unsafe
      AClass o3 = (AClass)unsafe.allocateInstance(AClass.class);
      // prints 0
      System.out.println(o3);
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe.getMessage());
    }
  }
}
