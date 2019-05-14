package design.bridge;

import java.util.concurrent.TimeUnit;

public class TestWorkShop extends WorkShop {

  public TestWorkShop() {
    super();
  }
  
  @Override
  public void work(Vehicle vehicle) {
    System.out.print("Testing... ");
    long timeToTake = 50 * vehicle.minWorkTime();
    try {
      TimeUnit.MILLISECONDS.sleep(timeToTake); // Thread.sleep(timeToTake);
    } catch (InterruptedException exp) {
      // nothing to do for now.
    } 
    System.out.printf("(Time taken: %d millis), Done.\n", timeToTake);
  }
}
