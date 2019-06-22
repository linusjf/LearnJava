package design.bridge;

/**
 * Describe class <code>Bus</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
public class Bus extends Vehicle {
  @Override
  public void manufacture() {
    System.out.println("Manufacturing Bus");
    workshops.stream().forEach(workshop -> workshop.work(this));
    System.out.println("Done.");
    System.out.println();
  }

  @Override
  public int minWorkTime() {
    return 20;
  }
}
