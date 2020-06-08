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

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Throws(unsafe=" + this.unsafe + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Throws)) return false;
    Throws other = (Throws) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$unsafe = this.unsafe;
    Object other$unsafe = other.unsafe;
    if (this$unsafe == null ? other$unsafe != null : !this$unsafe.equals(other$unsafe)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Throws;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $unsafe = this.unsafe;
    result = result * PRIME + ($unsafe == null ? 43 : $unsafe.hashCode());
    return result;
  }
}
