package unsafe;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;               // NOPMD
import sun.reflect.ReflectionFactory; // NOPMD

@SuppressWarnings({"PMD.SystemPrintln",
                   "PMD.DataflowAnomalyAnalysis",
                   "PMD.LawOfDemeter",
                   "PMD.TooManyMethods"})
public class UnsafeTest {

  private Unsafe unsafe;

  @Before
  public void prepareUnsafe() throws ReflectiveOperationException {
    unsafe = makeInstance();
  }

  private Unsafe makeInstance() throws ReflectiveOperationException {
    Constructor<Unsafe> unsafeConstructor =
        Unsafe.class.getDeclaredConstructor();
    unsafeConstructor.setAccessible(true);
    unsafe = unsafeConstructor.newInstance();
    return unsafe;
  }

  private Unsafe fetchInstance() throws ReflectiveOperationException {
    Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
    theUnsafe.setAccessible(true);
    unsafe = (Unsafe)theUnsafe.get(null);
    return unsafe;
  }

  @SuppressWarnings("PMD.DetachedTestCase")
  public void throwChecked() {
    unsafe.throwException(new Exception());
  }

  public void place(Object o, long address)
      throws ReflectiveOperationException {
    Class<?> clazz = o.getClass();
    while (clazz != null) {
      for (Field f: clazz.getDeclaredFields()) {
        if (!Modifier.isStatic(f.getModifiers())) {
          long offset = unsafe.objectFieldOffset(f);
          if (f.getType() == long.class) {
            unsafe.putLong(address + offset, unsafe.getLong(o, offset));
          } else if (f.getType() == int.class) {
            unsafe.putInt(address + offset, unsafe.getInt(o, offset));
          } else {
            throw new UnsupportedOperationException();
          }
        }
      }
      clazz = clazz.getSuperclass();
    }
  }

  public Object read(Class<?> c, long address)
      throws ReflectiveOperationException {
    Class<?> clazz = c;
    Object instance = unsafe.allocateInstance(clazz);
    while (clazz != null) {
      for (Field f: clazz.getDeclaredFields()) {
        if (!Modifier.isStatic(f.getModifiers())) {
          long offset = unsafe.objectFieldOffset(f);
          if (f.getType() == long.class) {
            unsafe.putLong(instance, offset, unsafe.getLong(address + offset));
          } else if (f.getType() == int.class) {
            unsafe.putInt(instance, offset, unsafe.getInt(address + offset));
          } else {
            throw new UnsupportedOperationException();
          }
        }
      }
      clazz = clazz.getSuperclass();
    }
    return instance;
  }

  public long sizeOf(Class<?> c) {
    long maximumOffset = 0L;
    Class<?> clazz = c;
    while (clazz != null) {
      for (Field f: clazz.getDeclaredFields()) {
        if (!Modifier.isStatic(f.getModifiers())) {
          maximumOffset = Math.max(maximumOffset, unsafe.objectFieldOffset(f));
        }
      }
      clazz = clazz.getSuperclass();
    }
    return maximumOffset + 8;
  }

  @Test
  public void testRetrieval() throws ReflectiveOperationException {
    Unsafe unsafeObj = fetchInstance();
    assertNotNull("Unsafe object not null", unsafeObj);
  }

  @Test(expected = SecurityException.class)
  public void testSingletonGetter() throws ReflectiveOperationException {
    Unsafe.getUnsafe();
  }

  @Test
  public void testObjectCreation() throws ReflectiveOperationException {
    ClassWithExpensiveConstructor instance =
        (ClassWithExpensiveConstructor)unsafe.allocateInstance(
            ClassWithExpensiveConstructor.class);
    assertEquals("Expensive constructor not invoked", 0, instance.getValue());
  }

  @Test
  public void testReflectionFactory() throws ReflectiveOperationException {
    @SuppressWarnings("unchecked")
    Constructor<?> silentConstructor =
        ReflectionFactory.getReflectionFactory().newConstructorForSerialization(
            ClassWithExpensiveConstructor.class, Object.class.getConstructor());
    silentConstructor.setAccessible(true);
    ClassWithExpensiveConstructor cwec =
        (ClassWithExpensiveConstructor)silentConstructor.newInstance();
    assertEquals("Constructor not invoked", 0, cwec.getValue());
  }

  @Test
  public void testStrangeReflectionFactory()
      throws ReflectiveOperationException {
    @SuppressWarnings("unchecked")
    Constructor<?> silentConstructor =
        ReflectionFactory.getReflectionFactory().newConstructorForSerialization(
            ClassWithExpensiveConstructor.class,
            OtherClass.class.getDeclaredConstructor());
    silentConstructor.setAccessible(true);
    ClassWithExpensiveConstructor instance =
        (ClassWithExpensiveConstructor)silentConstructor.newInstance();
    assertEquals("Constructor invoked", 10, instance.getValue());
    // assertEquals("Same class object",
    //     ClassWithExpensiveConstructor.class,
    //       instance.getClass());
    // assertEquals("Superclass is Object",
    //           Object.class,
    //         instance.getClass().getSuperclass());
  }

  @Test
  public void testDirectIntArray() throws Exception {
    long maximum = Integer.MAX_VALUE + 1L;
    DirectIntArray directIntArray = new DirectIntArray(maximum);
    directIntArray.setValue(0L, 2);
    directIntArray.setValue(maximum, 1);
    assertTrue("indexes set",
               2 == directIntArray.getValue(0L)
                   && 1 == directIntArray.getValue(maximum));
    directIntArray.destroy();
  }

  @Test
  public void testMallaciousAllocation() throws Exception {
    long address = unsafe.allocateMemory(2L * 4);
    unsafe.setMemory(address, 8L, (byte)0);
    // assertEquals(0, unsafe.getInt(address));
    // assertEquals(0, unsafe.getInt(address + 4));
    unsafe.putInt(address + 1, 0xffffffff);
    assertTrue("Address values tested",
               0xffffff00 == unsafe.getInt(address)
                   && 0x000000ff == unsafe.getInt(address + 4));
  }

  @Test
  public void testObjectAllocation() throws Exception {
    long containerSize = sizeOf(Container.class);
    long address = unsafe.allocateMemory(containerSize);
    Container c1 = new Container(10, 1000L);
    Container c2 = new Container(5, -10L);
    place(c1, address);
    place(c2, address + containerSize);
    Container newC1 = (Container)read(Container.class, address);
    Container newC2 = (Container)read(Container.class, address + containerSize);
    assertTrue("Objects are equal", c1.equals(newC1) && c2.equals(newC2));
  }

  @Test(expected = Exception.class)
  public void testThrowChecked() throws Exception {
    throwChecked();
  }

  @Test
  public void testPark() throws Exception {
    final boolean[] run = new boolean[1];
    Thread thread = new Thread() {
      @Override
      public void run() {
        unsafe.park(true, 100_000L);
        run[0] = true;
      }
    };
    thread.start();
    unsafe.unpark(thread);
    thread.join(100L);
    assertTrue("Set true", run[0]);
  }

  @Test
  public void testCopy() throws Exception {
    long address = unsafe.allocateMemory(4L);
    unsafe.putInt(address, 100);
    long otherAddress = unsafe.allocateMemory(4L);
    unsafe.copyMemory(address, otherAddress, 4L);
    assertEquals("Value equals 100", 100, unsafe.getInt(otherAddress));
  }

  @SuppressWarnings("PMD.SystemPrintln")
  private static final class ClassWithExpensiveConstructor {

    private final int value;

    private ClassWithExpensiveConstructor() {
      value = doExpensiveLookup();
    }

    private int doExpensiveLookup() {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        System.err.println(e.getMessage());
      }
      return 1;
    }

    public int getValue() {
      return value;
    }
  }

  @SuppressWarnings("PMD.SystemPrintln")
  private static final class OtherClass {

    private final int value;
    private final int unknownValue;

    private OtherClass() {
      System.out.println("test");
      this.value = 10;
      this.unknownValue = 20;
    }
  }

  private class DirectIntArray {

    private static final long INT_SIZE_IN_BYTES = 4;

    private final long startIndex;

    DirectIntArray(long size) {
      startIndex = unsafe.allocateMemory(size * INT_SIZE_IN_BYTES);
      unsafe.setMemory(startIndex, size * INT_SIZE_IN_BYTES, (byte)0);
    }

    public void setValue(long index, int value) {
      unsafe.putInt(index(index), value);
    }

    public int getValue(long index) {
      return unsafe.getInt(index(index));
    }

    private long index(long offset) {
      return startIndex + offset * INT_SIZE_IN_BYTES;
    }

    public void destroy() {
      unsafe.freeMemory(startIndex);
    }
  }

  private static class SuperContainer {

    protected int i;

    private SuperContainer(int i) {
      this.i = i;
    }

    public int getI() {
      return i;
    }
  }

  private static final class Container extends SuperContainer {

    private final long l;

    private Container(int i, long l) {
      super(i);
      this.l = l;
    }

    public long getL() {
      return l;
    }

    @Override
    public int hashCode() {
      return Objects.hash(i, l);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      Container container = (Container)o;
      return l == container.l && i == container.i;
    }
  }
}
