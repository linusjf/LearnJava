package unsafe;

@SuppressWarnings("PMD.DataClass")
public final class SampleClass extends SampleBaseClass {
  private static final byte B = 100;
  private int i = 5;
  private long l = 10;

  public SampleClass() {
    // empty constructor
  }

  public SampleClass(int i, long l) {
    this();
    this.i = i;
    this.l = l;
  }

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }

  public long getL() {
    return l;
  }

  public void setL(long l) {
    this.l = l;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "SampleClass(i=" + this.getI() + ", l=" + this.getL() + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof SampleClass)) return false;
    SampleClass other = (SampleClass) o;
    if (!other.canEqual((Object) this)) return false;
    if (!super.equals(o)) return false;
    if (this.getI() != other.getI()) return false;
    if (this.getL() != other.getL()) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof SampleClass;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = super.hashCode();
    result = result * PRIME + this.getI();
    long $l = this.getL();
    result = result * PRIME + (int) ($l >>> 32 ^ $l);
    return result;
  }
//  public static byte getB() {
  //  return B;
  //}
}
