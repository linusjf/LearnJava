package unsafe;

import static unsafe.UnsafeUtils.*;

import java.lang.reflect.Field;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings({"PMD.SystemPrintln",
                   "PMD.FinalFieldCouldBeStatic",
                   "PMD.LawOfDemeter",
                   "PMD.ImmutableField",
                   "restriction"})
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
}
