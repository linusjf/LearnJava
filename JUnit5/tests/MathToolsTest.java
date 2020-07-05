package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

class MathToolsTest {
  @RepeatedTest(Integer.MAX_VALUE)
  void testConvertToDecimalSuccess(RepetitionInfo ri) {
    int iteration = ri.getCurrentRepetition();
    double result = MathTools.convertToDecimal(iteration, Integer.MAX_VALUE);
    assertEquals((float)iteration / (float)Integer.MAX_VALUE,
                 result,
                 0.0001d,
                 "Values are equal");
  }

  @Test
  void testConvertToDecimalInvalidDenominator() {
    assertThrows(IllegalArgumentException.class,
                 () -> MathTools.convertToDecimal(3, 0));
  }
}
