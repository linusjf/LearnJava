package unsafe;

@SuppressWarnings("PMD.DataClass")
public final class SampleClass extends SampleBaseClass {

  private static final byte B = 100;

  private int i = 5;
  private long l = 10;

  public SampleClass() {
    super();
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

  public static byte getB() {
    return B;
  }
}
