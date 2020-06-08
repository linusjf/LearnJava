package unsafe;

import static unsafe.UnsafeUtils.*;

@SuppressWarnings("PMD.LawOfDemeter")
public class SuperArray {
  private static final int BYTE = 1;
  private static final long SUPER_SIZE = (long) Integer.MAX_VALUE * 2;
  private final long size;
  private final long address;

  public SuperArray(long size) throws ReflectiveOperationException {
    this.size = size;
    address = getUnsafe().allocateMemory(size * BYTE);
  }

  public void set(long i, byte value) throws ReflectiveOperationException {
    getUnsafe().putByte(address + i * BYTE, value);
  }

  public int get(long idx) throws ReflectiveOperationException {
    return getUnsafe().getByte(address + idx * BYTE);
  }

  public long getSize() {
    return size;
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String... args) {
    try {
      SuperArray array = new SuperArray(SUPER_SIZE);
      System.out.println("Array size:" + array.getSize());
      // 4294967294
      long sum = 0L;
      for (int i = 0; i < 100; i++) {
        array.set((long) Integer.MAX_VALUE + i, (byte) 3);
        sum += array.get((long) Integer.MAX_VALUE + i);
      }
      // 300
      System.out.println("Sum of 100 elements:" + sum);
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe.getMessage());
    }
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "SuperArray(size=" + this.getSize() + ", address=" + this.address + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof SuperArray)) return false;
    SuperArray other = (SuperArray) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getSize() != other.getSize()) return false;
    if (this.address != other.address) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof SuperArray;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $size = this.getSize();
    result = result * PRIME + (int) ($size >>> 32 ^ $size);
    long $address = this.address;
    result = result * PRIME + (int) ($address >>> 32 ^ $address);
    return result;
  }
}
