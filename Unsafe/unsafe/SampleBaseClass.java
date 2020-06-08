package unsafe;

@SuppressWarnings("PMD.AvoidUsingShortType")
public class SampleBaseClass {
  protected short s = 20;

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "SampleBaseClass(s=" + this.s + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof SampleBaseClass)) return false;
    SampleBaseClass other = (SampleBaseClass) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.s != other.s) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof SampleBaseClass;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.s;
    return result;
  }
  // empty class, no methods
}
