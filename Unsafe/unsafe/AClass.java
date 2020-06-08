package unsafe;

import static unsafe.UnsafeUtils.*;
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

  @SuppressWarnings({"PMD.SystemPrintln", "PMD.LawOfDemeter", "restriction"})
  public static void main(String... args) {
    try {
      Unsafe unsafe = getUnsafe();
      // constructor
      AClass o1 = new AClass();
      // prints 1
      System.out.println(o1);
      // reflection
      AClass o2 = AClass.class.getDeclaredConstructor().newInstance();
      // prints 1
      System.out.println(o2);
      // unsafe
      AClass o3 = (AClass) unsafe.allocateInstance(AClass.class);
      // prints 0
      System.out.println(o3);
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe.getMessage());
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof AClass)) return false;
    AClass other = (AClass) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.a != other.a) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof AClass;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $a = this.a;
    result = result * PRIME + (int) ($a >>> 32 ^ $a);
    return result;
  }
}
