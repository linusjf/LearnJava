package design.bridge;

import java.util.concurrent.TimeUnit;

/**
 * Describe class <code>PaintWorkShop</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class PaintWorkShop extends WorkShop {

  @Override
  public void work(Vehicle vehicle) {
    System.out.print("Painting... ");
    long timeToTake = 100L * vehicle.minWorkTime();
    try {
      TimeUnit.MILLISECONDS.sleep(timeToTake);
      // Thread.sleep(timeToTake);
    } catch (InterruptedException exp) {
      // nothing to do for now.
    }
    System.out.printf("(Time taken: %d millis), Done.%n", timeToTake);
  }
}
