package eu.javaspecialists.reflection;

import static org.junit.jupiter.api.Assertions.*;

import eu.javaspecialists.reflection.Human.HumanState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@TestInstance(Lifecycle.PER_CLASS)
@Execution(ExecutionMode.SAME_THREAD)
@DisplayName("EnumSwitchTest")
@SuppressWarnings("PMD")
public class EnumSwitchTest {
  private static final String UNKNOWN_STATE = "Unknown state";

  @Test
  @DisplayName("EnumSwitchTest.testSingingDeletingEnum")
  public void testSingingDeletingEnum() throws ReflectiveOperationException {
    EnumBuster<HumanState> buster =
        new EnumBuster<>(HumanState.class, EnumSwitchTest.class);
    try {
      for (HumanState state: HumanState.values()) {
        switch (state) {
          case HAPPY:
          case SAD:
            break;
          default:
            fail(UNKNOWN_STATE);
        }
      }

      buster.deleteByValue(HumanState.HAPPY);
      for (HumanState state: HumanState.values()) {
        switch (state) {
          case SAD:
            break;
          case HAPPY:
          default:
            fail(UNKNOWN_STATE);
        }
      }

      buster.undo();
      buster.deleteByValue(HumanState.SAD);
      for (HumanState state: HumanState.values()) {
        switch (state) {
          case HAPPY:
            break;
          case SAD:
          default:
            fail(UNKNOWN_STATE);
        }
      }

      buster.deleteByValue(HumanState.HAPPY);
      for (HumanState state: HumanState.values()) {
        switch (state) {
          case HAPPY:
          case SAD:
          default:
            fail(UNKNOWN_STATE);
        }
      }
    } finally {
      buster.restore();
    }
  }
}
