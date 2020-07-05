package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.SAME_THREAD)
class MathToolsTest {
  @Test
  void testConvertToDecimalSuccess() {
    double result =
        MathTools.convertToDecimal(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
    assertNotEquals((float)(Integer.MAX_VALUE - 1) / (float)Integer.MAX_VALUE,
                    result,
                    0.000_000_000_000_001d,
                    "Values are equal");
  }

  @Test
  void testConvertToDecimalInvalidDenominator() {
    assertThrows(IllegalArgumentException.class,
                 () -> MathTools.convertToDecimal(3, 0));
  }
}
