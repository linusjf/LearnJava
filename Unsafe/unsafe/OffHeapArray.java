package unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings("PMD.LawOfDemeter")
public class OffHeapArray {
  private static final int BYTE = 1;
  private final long size;
  private final long address;

  public OffHeapArray(long size)
      throws NoSuchFieldException, IllegalAccessException {
    this.size = size;
    address = getUnsafe().allocateMemory(size * BYTE);
  }

  private Unsafe getUnsafe()
      throws IllegalAccessException, NoSuchFieldException {
    Field f = Unsafe.class.getDeclaredField("theUnsafe");
    f.setAccessible(true);
    return (Unsafe)f.get(null);
  }

  public void set(long i, byte value)
      throws NoSuchFieldException, IllegalAccessException {
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
}
