package design.bridge;

/**
 * Describe class <code>Bike</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Bike extends Vehicle {
  @Override
  public void manufacture() {
    System.out.println("Manufacturing Bike...");
    workshops.stream().forEach(workshop -> workshop.work(this));
    System.out.println("Done.");
    System.out.println();
  }

  @Override
  public int minWorkTime() {
    return 5;
  }
}
