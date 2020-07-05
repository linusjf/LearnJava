package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

class MathToolsTest {
  @RepeatedTest(10_000)
  void testConvertToDecimalSuccess(RepetitionInfo ri) {
    int iteration = ri.getCurrentRepetition();
    double result = MathTools.convertToDecimal(iteration, 10_000);
    assertEquals((float)iteration / (float)10_000,
                 result,
                 0.000_000_001d,
                 "Values are equal");
  }

  @Test
  void testConvertToDecimalInvalidDenominator() {
    assertThrows(IllegalArgumentException.class,
                 () -> MathTools.convertToDecimal(3, 0));
  }
}
