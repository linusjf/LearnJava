package unsafe;

import static unsafe.UnsafeUtils.*;

import sun.misc.Unsafe; // NOPMD

@SuppressWarnings("restriction")
public class TypeSafety {
  private Object f = new Object();  // NOPMD

  @SuppressWarnings({"PMD.SystemPrintln", "PMD.LawOfDemeter"})
  public static void main(String... args) {
    try {
      Unsafe unsafe = getUnsafe();
      long fieldOffset =
          unsafe.objectFieldOffset(TypeSafety.class.getDeclaredField("f"));
      TypeSafety ts = new TypeSafety();
      System.out.println(ts.f);
      // f now points to nirvana
      // this crashes the jvm
      unsafe.putInt(ts, fieldOffset, 1_234_567_890);
      System.out.println(ts.f);
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe.getMessage());
    }
  }
}
