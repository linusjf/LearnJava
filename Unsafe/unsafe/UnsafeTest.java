package unsafe;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import sun.misc.Unsafe;               // NOPMD
import sun.reflect.ReflectionFactory; // NOPMD

@SuppressWarnings({"PMD.SystemPrintln",
                   "PMD.DataflowAnomalyAnalysis",
                   "PMD.LawOfDemeter",
                   "PMD.TooManyMethods"})
@TestInstance(Lifecycle.PER_CLASS)
public class UnsafeTest {

  private Unsafe unsafe;

  @BeforeAll
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
    assertNotNull(unsafeObj, "Unsafe object not null");
  }

  @Test
  public void testSingletonGetter() throws ReflectiveOperationException {
    assertThrows(SecurityException.class,
                 ()
                     -> Unsafe.getUnsafe(),
                 "Throws java.lang.SecurityException");
  }

  @Test
  public void testObjectCreation() throws ReflectiveOperationException {
    ClassWithExpensiveConstructor instance =
        (ClassWithExpensiveConstructor)unsafe.allocateInstance(
            ClassWithExpensiveConstructor.class);
    assertEquals(0, instance.getValue(), "Expensive constructor not invoked");
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
    assertEquals(0, cwec.getValue(), "Constructor not invoked");
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
    assertEquals(10, instance.getValue(), "Constructor invoked");
  }

  @Disabled
  @Test
  public void testDirectIntArray() throws Exception {
    long maximum = Integer.MAX_VALUE + 1L;
    DirectIntArray directIntArray = new DirectIntArray(maximum);
    directIntArray.setValue(0L, 2);
    directIntArray.setValue(maximum, 1);
    assertTrue(2 == directIntArray.getValue(0L)
                   && 1 == directIntArray.getValue(maximum),
               "indexes set");
    directIntArray.destroy();
  }

  @Test
  public void testOffHeapArray() throws Exception {

    long maximum = Integer.MAX_VALUE + 1L;
    OffHeapArray array = new OffHeapArray(maximum);
    array.set(0L, (byte)2);
    array.set(maximum, (byte)1);
    assertTrue(2 == array.get(0L) && 1 == array.get(maximum), "indexes set");
    array.freeMemory();
  }

  @Test
  public void testCASCounterOrdered() throws InterruptedException {
    int numThreads = 1_000;
    int numIncrements = 10_000;
    ExecutorService service = Executors.newFixedThreadPool(numThreads);
    CASCounter casCounter = new CASCounter();

    IntStream.rangeClosed(0, numThreads - 1)
        .forEach(i
                 -> service.submit(
                     ()
                         -> IntStream.rangeClosed(0, numIncrements - 1)
                                .forEachOrdered(j -> casCounter.increment())));
    service.shutdown();
    service.awaitTermination(1, TimeUnit.MINUTES);
    assertEquals((long)numIncrements * (long)numThreads,
                 casCounter.get(),
                 "Counter has expected value");
  }

  @Test
  public void testCASCounterParallel() throws InterruptedException {
    int numThreads = 1_000;
    int numIncrements = 10_000;
    ExecutorService service = Executors.newFixedThreadPool(numThreads);
    CASCounter casCounter = new CASCounter();

    IntStream.rangeClosed(0, numThreads - 1)
        .parallel()
        .forEach(i
                 -> service.submit(
                     ()
                         -> IntStream.rangeClosed(0, numIncrements - 1)
                                .forEach(j -> casCounter.increment())));
    service.shutdown();
    service.awaitTermination(1, TimeUnit.MINUTES);
    assertEquals((long)numIncrements * (long)numThreads,
                 casCounter.get(),
                 "Counter has expected value");
  }

  @Test
  public void testCASCounterClient() throws InterruptedException {
    int numThreads = 1_000;
    int numIncrements = 10_000;
    ExecutorService service = Executors.newFixedThreadPool(numThreads);
    CASCounter casCounter = new CASCounter();

    IntStream.rangeClosed(0, numThreads - 1)
        .forEach(
            i -> service.submit(new CounterClient(casCounter, numIncrements)));
    service.shutdown();
    service.awaitTermination(1, TimeUnit.MINUTES);
    assertEquals((long)numIncrements * (long)numThreads,
                 casCounter.get(),
                 "Counter has expected value");
  }

  @Test
  public void testMallaciousAllocation() throws Exception {
    long address = unsafe.allocateMemory(2L * 4);
    unsafe.setMemory(address, 8L, (byte)0);
    unsafe.putInt(address + 1, 0xffffffff);
    assertTrue(0xffffff00 == unsafe.getInt(address)
                   && 0x000000ff == unsafe.getInt(address + 4),
               "Address values tested");
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
    assertTrue(c1.equals(newC1) && c2.equals(newC2), "Objects are equal");
  }

  @Test
  public void testThrowChecked() throws Exception {
    assertThrows(
        Exception.class, () -> throwChecked(), "Throws java.lang.Exception");
  }

  @Test
  public void testPark() throws Exception {
    final boolean[] run = new boolean[1];
    Thread thread = new Thread(() -> {
      unsafe.park(true, 100_000L);
      run[0] = true;
    });
    thread.start();
    unsafe.unpark(thread);
    thread.join(100L);
    assertTrue(run[0], "Set true");
  }

  @Test
  public void testCopy() throws Exception {
    long address = unsafe.allocateMemory(4L);
    unsafe.putInt(address, 100);
    long otherAddress = unsafe.allocateMemory(4L);
    unsafe.copyMemory(address, otherAddress, 4L);
    assertEquals(100, unsafe.getInt(otherAddress), "Value equals 100");
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
      return ThreadLocalRandom.current().nextInt();
    }

    public int getValue() {
      return value;
    }
  }

  @SuppressWarnings("PMD.SystemPrintln")
  private static final class OtherClass {

    final int value;
    final int unknownValue;

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
