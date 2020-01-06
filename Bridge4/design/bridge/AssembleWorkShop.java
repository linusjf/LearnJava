package design.bridge;

import java.util.concurrent.TimeUnit;

/**
 * Describe class <code>AssembleWorkShop</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class AssembleWorkShop extends WorkShop {
  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  @Override
  public void work(Vehicle vehicle) {
    System.out.print("Assembling... ");
    long timeToTake = 200L * vehicle.minWorkTime();
    try {
      TimeUnit.MILLISECONDS.sleep(timeToTake);
    } catch (InterruptedException exp) {
      // nothing to do for now.
    }
    System.out.printf("(Time taken: %d millis), Done.%n", timeToTake);
  }
}
