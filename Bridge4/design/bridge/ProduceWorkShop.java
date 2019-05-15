package design.bridge;

import java.util.concurrent.TimeUnit;

/**
 * Describe class <code>ProduceWorkShop</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ProduceWorkShop extends WorkShop {

  /** Creates a new <code>ProduceWorkShop</code> instance. */
  public ProduceWorkShop() {
    super();
  }

  @Override
  public void work(Vehicle vehicle) {
    System.out.print("Producing... ");
    long timeToTake = 300 * vehicle.minWorkTime();
    try {
      TimeUnit.MILLISECONDS.sleep(timeToTake); // Thread.sleep(timeToTake);
    } catch (InterruptedException exp) {
      // nothing to do for now.
    }
    System.out.printf("(Time taken: %d millis), Done.\n", timeToTake);
  }
}
