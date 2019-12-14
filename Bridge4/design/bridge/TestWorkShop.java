package design.bridge;

import java.util.concurrent.TimeUnit;

/**
 * Describe class <code>TestWorkShop</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class TestWorkShop extends WorkShop {
  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void work(Vehicle vehicle) {
    System.out.print("Testing... ");
    long timeToTake = 50L * vehicle.minWorkTime();
    try {
      TimeUnit.MILLISECONDS.sleep(timeToTake);
    } catch (InterruptedException exp) {
      // nothing to do for now.
    }
    System.out.printf("(Time taken: %d millis), Done.%n", timeToTake);
  }
}
