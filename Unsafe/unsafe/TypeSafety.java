package unsafe;

import static unsafe.UnsafeUtils.*;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings("restriction")
public class TypeSafety {
  private Object f = new Object(); // NOPMD

  @SuppressWarnings({"PMD.SystemPrintln", "PMD.LawOfDemeter"})
  public static void main(String... args) {
    try {
      Unsafe unsafe = getUnsafe();
      long fieldOffset = unsafe.objectFieldOffset(TypeSafety.class.getDeclaredField("f"));
      TypeSafety ts = new TypeSafety();
      System.out.println(ts.f);
      // f now points to nirvana
      // this crashes the jvm
      unsafe.putInt(ts, fieldOffset, 1234567890);
      System.out.println(ts.f);
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe.getMessage());
    }
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "TypeSafety(f=" + this.f + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof TypeSafety)) return false;
    TypeSafety other = (TypeSafety) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$f = this.f;
    Object other$f = other.f;
    if (this$f == null ? other$f != null : !this$f.equals(other$f)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof TypeSafety;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $f = this.f;
    result = result * PRIME + ($f == null ? 43 : $f.hashCode());
    return result;
  }
}
