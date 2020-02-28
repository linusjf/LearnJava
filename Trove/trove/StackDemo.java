package trove;

import gnu.trove.stack.array.TDoubleArrayStack;
import java.util.Stack;
import java.util.logging.Logger;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public enum StackDemo {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(StackDemo.class.getName());

  private static final int COUNT = 15_000;

  public static void main(String... args) {
    long start = System.currentTimeMillis();
    LOGGER.info(() -> "Start time: " + start);
    performJDK();
    LOGGER.info(() -> "Time taken: " + (System.currentTimeMillis() - start));
    long start2 = System.currentTimeMillis();
    LOGGER.info(() -> "Start time: " + start2);
    performTrove();
    LOGGER.info(() -> "Time taken: " + (System.currentTimeMillis() - start2));
  }

  private static void performJDK() {
    for (int i = 0; i < COUNT; i++)
      demonstrateJDKDoubleStack();
  }

  private static void performTrove() {
    for (int i = 0; i < COUNT; i++)
      demonstrateTroveDoubleArrayStack();
  }

  /**
   * Demonstrate Trove's Double Array Stack.
   *
   * <p>Trove's TDoubleArrayStack allows access to its contents via push, pop, and peek.
   */
  public static void demonstrateTroveDoubleArrayStack() {
    final TDoubleArrayStack stack = new TDoubleArrayStack();
    stack.push(15.5);
    stack.push(17.3);
    stack.push(16.6);
    stack.push(2.2);
    LOGGER.fine(() -> "Trove Array Stack of Doubles");
    LOGGER.fine(
        () -> "\tPeek: " + stack.peek() + "; After Size: " + stack.size());
    LOGGER.fine(
        () -> "\tPop:  " + stack.pop() + "; After Size: " + stack.size());
    LOGGER.fine(
        () -> "\tPeek: " + stack.peek() + "; After Size: " + stack.size());
  }

  /**
   * Demonstrate JDK Stack.
   *
   * <p>JDK Stack allows access to its contents via push, pop, and peek.
   */
  public static void demonstrateJDKDoubleStack() {
    final Stack<Double> stack = new Stack<>();
    stack.push(15.5);
    stack.push(17.3);
    stack.push(16.6);
    stack.push(2.2);
    LOGGER.fine(() -> "JDK Stack of Doubles");
    LOGGER.fine(
        () -> "\tPeek: " + stack.peek() + "; After Size: " + stack.size());
    LOGGER.fine(
        () -> "\tPop:  " + stack.pop() + "; After Size: " + stack.size());
    LOGGER.fine(
        () -> "\tPeek: " + stack.peek() + "; After Size: " + stack.size());
  }
}
