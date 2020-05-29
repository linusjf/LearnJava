package unsafe;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings({"PMD.SystemPrintln",
                   "PMD.DataflowAnomalyAnalysis",
                   "PMD.LawOfDemeter",
                   "PMD.TooManyMethods"})
@TestInstance(Lifecycle.PER_CLASS)
public class SampleTest {

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
    assertNotEquals(address, 0L, "Address not zero");
  }

  @Test
  public void testObjectAddress() {
    SampleClass sampleClassObject = new SampleClass();
    long address = getObjectAddress(sampleClassObject);
    System.out.println(address);
    assertNotEquals(address, 0L, "Address not zero");
  }
}
