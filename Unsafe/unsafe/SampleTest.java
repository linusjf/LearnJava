package unsafe;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import org.junit.Before;
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
}
