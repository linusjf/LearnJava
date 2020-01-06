package design.bridge;

import java.util.concurrent.TimeUnit;

/**
 * Describe class <code>ProduceWorkShop</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ProduceWorkShop extends WorkShop {
  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  @Override
  public void work(Vehicle vehicle) {
    System.out.print("Producing... ");
    long timeToTake = 300L * vehicle.minWorkTime();
    try {
      TimeUnit.MILLISECONDS.sleep(timeToTake);
    } catch (InterruptedException exp) {
      // nothing to do for now.
    }
    System.out.printf("(Time taken: %d millis), Done.%n", timeToTake);
  }
}
