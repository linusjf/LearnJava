package eu.javaspecialists.reflection;

import static org.junit.jupiter.api.Assertions.*;

import eu.javaspecialists.reflection.Human.HumanState;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@TestInstance(Lifecycle.PER_CLASS)
@Execution(ExecutionMode.SAME_THREAD)
@DisplayName("HumanTest")
@SuppressWarnings("PMD")
public class HumanTest {
  @Test
  @DisplayName("HumanTest.testSingingAddingEnum")
  public void testSingingAddingEnum() throws ReflectiveOperationException {
    EnumBuster<HumanState> buster =
        new EnumBuster<>(HumanState.class, Human.class);
    try {
      Human heinz = new Human();
      heinz.sing(HumanState.HAPPY);
      heinz.sing(HumanState.SAD);

      HumanState MELLOW = buster.make("MELLOW");
      buster.addByValue(MELLOW);
      System.out.println(Arrays.toString(HumanState.values()));

      try {
        heinz.sing(MELLOW);
        fail("Should have caused an IllegalStateException");
      } catch (IllegalStateException ignoredSuccess) {
        // empty catch block
      }
    } finally {
      System.out.println("Restoring HumanState");
      buster.restore();
      System.out.println(Arrays.toString(HumanState.values()));
    }
  }
}
