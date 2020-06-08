package unsafe;

import static unsafe.UnsafeUtils.*;
import java.lang.reflect.Field;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings({"PMD.SystemPrintln", "PMD.FinalFieldCouldBeStatic", "PMD.LawOfDemeter", "PMD.ImmutableField", "restriction"})
public class Guard {
  private int accessAllowed = 1;

  public boolean giveAccess() {
    return 42 == accessAllowed;
  }

  public static void main(String... args) {
    try {
      Guard guard = new Guard();
      // false, no access
      System.out.println(guard.giveAccess());
      // bypass
      Unsafe unsafe = getUnsafe();
      Field f = Guard.class.getDeclaredField("accessAllowed");
      // memory corruption
      unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
      // true, access granted
      System.out.println(guard.giveAccess());
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe.getMessage());
    }
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Guard(accessAllowed=" + this.accessAllowed + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Guard)) return false;
    Guard other = (Guard) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.accessAllowed != other.accessAllowed) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Guard;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.accessAllowed;
    return result;
  }
}
