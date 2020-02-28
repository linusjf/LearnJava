package trove;

import gnu.trove.list.array.TDoubleArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public enum Demo {
  ;

  private static final Logger LOGGER = Logger.getLogger(Demo.class.getName());

  private static final int COUNT = 15_000;

  public static void main(String... args) {
    long start = System.currentTimeMillis();
    LOGGER.info(() -> "Start time: " + start);
    iterateJDK();
    LOGGER.info(() -> "Time taken: " + (System.currentTimeMillis() - start));
    long start2 = System.currentTimeMillis();
    LOGGER.info(() -> "Start time: " + start2);
    iterateTrove();
    LOGGER.info(() -> "Time taken: " + (System.currentTimeMillis() - start2));
  }

  private static void iterateJDK() {
    for (int i = 0; i < COUNT; i++)
      demonstrateJdkArrayListForDoubles();
  }

  private static void iterateTrove() {
    for (int i = 0; i < COUNT; i++)
      demonstrateTroveArrayListForDoubles();
  }

  // Demonstrate standard JDK {@code ArrayList<Double>} with some JDK 8 functionality.
  public static void demonstrateJdkArrayListForDoubles() {
    final List<Double> doubles = new ArrayList<>();
    doubles.add(15.5);
    doubles.add(24.4);
    doubles.add(36.3);
    doubles.add(67.6);
    doubles.add(10.0);
    LOGGER.fine("JDK ArrayList<Double>:");
    LOGGER.fine(() -> "\tDoubles List: " + doubles);
    LOGGER.fine(
        () -> "\tMaximum double: " + doubles.stream().max(Double::compare));
    LOGGER.fine(
        () -> "\tMinimum double: " + doubles.stream().min(Double::compare));
    LOGGER.fine(
        ()
            -> "\tSum of doubles: "
                   + doubles.stream().mapToDouble(Double::doubleValue).sum());
  }

  /**
   * Demonstrate use of TDoubleArrayList and show how similar using it is to using {@code
   * ArrayList<Double>}.
   */
  public static void demonstrateTroveArrayListForDoubles() {
    // Demonstrate adding elements to TDoubleArrayList is
    // exactly like adding elements to ArrayList<Double>.
    final TDoubleArrayList doubles = new TDoubleArrayList();
    doubles.add(15.5);
    doubles.add(24.4);
    doubles.add(36.3);
    doubles.add(67.6);
    doubles.add(10.0);
    LOGGER.fine(() -> "Trove TDoubleArrayList:");
    // TDoubleArrayList overrides toString()
    LOGGER.fine(() -> "\tDoubles List: " + doubles);
    LOGGER.fine(() -> "\tMaximum double: " + doubles.max());
    LOGGER.fine(() -> "\tMinimum double: " + doubles.min());
    LOGGER.fine(() -> "\tSum of doubles: " + doubles.sum());
  }
}
