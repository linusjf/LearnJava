package design.bridge;

import java.util.concurrent.TimeUnit;

/**
 * Describe class <code>RepairWorkShop</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class RepairWorkShop extends WorkShop {

  @Override
  public void work(Vehicle vehicle) {
    System.out.print("Repairing... ");
    long timeToTake = 150 * vehicle.minWorkTime();
    try {
      TimeUnit.MILLISECONDS.sleep(timeToTake); // Thread.sleep(timeToTake);
    } catch (InterruptedException exp) {
      // nothing to do for now.
    }
    System.out.printf("(Time taken: %d millis), Done.\n", timeToTake);
  }
}
