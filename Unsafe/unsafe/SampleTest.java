package unsafe;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings({"PMD.SystemPrintln",
                   "PMD.DataflowAnomalyAnalysis",
                   "PMD.LawOfDemeter",
                   "PMD.TooManyMethods"})
public class SampleTest {

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

  private long getObjectAddress(Object obj) {
    Object[] helperArray = new Object[1];
    helperArray[0] = obj;
    long baseOffset = unsafe.arrayBaseOffset(Object[].class);
    return unsafe.getLong(helperArray, baseOffset);
  }

  @Test
  public void testClassAddress() {
    SampleClass sampleClassObject = new SampleClass();
    long address = unsafe.getLong(sampleClassObject, 8L);
    System.out.println(address);
    assertNotEquals("Address not zero", address, 0L);
  }

  @Test
  public void testObjectAddress() {
    SampleClass sampleClassObject = new SampleClass();
    long address = getObjectAddress(sampleClassObject);
    System.out.println(address);
    assertNotEquals("Address not zero", address, 0L);
  }
}
