/**
 * Describe class <code>BridgePattern</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class BridgePattern {

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    Vehicle vehicle1 = new Car(new Produce(), new Assemble());
    vehicle1.manufacture();

    Vehicle vehicle2 = new Bike(new Produce(), new Assemble());
    vehicle2.manufacture();
  }
}
