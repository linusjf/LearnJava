package design.bridge;

/**
 * Describe class <code>Car</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
public class Car extends Vehicle {
  @Override
  public void manufacture() {
    System.out.println("Manufacturing Car");
    workshops.stream().forEach(workshop -> workshop.work(this));
    System.out.println("Done.");
    System.out.println();
  }

  @Override
  public int minWorkTime() {
    return 10;
  }
}
