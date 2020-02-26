package trove;

import gnu.trove.list.array.TDoubleArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public enum Demo {
  ;

  private static Logger LOGGER = Logger.getLogger(Demo.class.getName());

  public static void main(String... args) {
    demonstrateJdkArrayListForDoubles();
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
    LOGGER.info("JDK ArrayList<Double>:");
    LOGGER.info(() -> "\tDoubles List: " + doubles);
    LOGGER.info(
        () -> "\tMaximum double: " + doubles.stream().max(Double::compare));
    LOGGER.info(
        () -> "\tMinimum double: " + doubles.stream().min(Double::compare));
    LOGGER.info(
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
    LOGGER.info(() -> "Trove TDoubleArrayList:");
    // TDoubleArrayList overrides toString()
    LOGGER.info(() -> "\tDoubles List: " + doubles);
    LOGGER.info(() -> "\tMaximum double: " + doubles.max());
    LOGGER.info(() -> "\tMinimum double: " + doubles.min());
    LOGGER.info(() -> "\tSum of doubles: " + doubles.sum());
  }
}
