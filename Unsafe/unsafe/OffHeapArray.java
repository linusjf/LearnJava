package unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings("PMD.LawOfDemeter")
public class OffHeapArray {
  private static final int BYTE = 1;
  private final long size;
  private final long address;

  public OffHeapArray(long size) throws NoSuchFieldException, IllegalAccessException {
    this.size = size;
    address = getUnsafe().allocateMemory(size * BYTE);
  }

  private Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
    Field f = Unsafe.class.getDeclaredField("theUnsafe");
    f.setAccessible(true);
    return (Unsafe) f.get(null);
  }

  public void set(long i, byte value) throws NoSuchFieldException, IllegalAccessException {
    getUnsafe().putByte(address + i * BYTE, value);
  }

  public int get(long idx) throws NoSuchFieldException, IllegalAccessException {
    return getUnsafe().getByte(address + idx * BYTE);
  }

  public long getSize() {
    return size;
  }

  public void freeMemory() throws NoSuchFieldException, IllegalAccessException {
    getUnsafe().freeMemory(address);
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "OffHeapArray(size=" + this.getSize() + ", address=" + this.address + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof OffHeapArray)) return false;
    OffHeapArray other = (OffHeapArray) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getSize() != other.getSize()) return false;
    if (this.address != other.address) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof OffHeapArray;
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
