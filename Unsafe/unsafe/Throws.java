package unsafe;

import static unsafe.UnsafeUtils.*;

import sun.misc.Unsafe; // NOPMD

@SuppressWarnings("restriction")
public class Throws {

  private final Unsafe unsafe;

  public Throws() throws ReflectiveOperationException {
    unsafe = getUnsafe();
  }

  void method() {
    unsafe.throwException(new Exception("Unsafe thrown exception"));
  }

  @SuppressWarnings({"PMD.SystemPrintln", "PMD.AvoidCatchingGenericException"})
  public static void main(String... args) {
    try {
      Throws t = new Throws();
      t.method();
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe.getMessage());
    } catch (Exception exc) {
      System.err.println("Exception: " + exc.getMessage());
    }
  }
}
