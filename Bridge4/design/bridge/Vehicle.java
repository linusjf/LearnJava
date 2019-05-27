package design.bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe class <code>Vehicle</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public abstract class Vehicle {
  // assembly line for the workshops
  protected List<WorkShop> workshops = new ArrayList<WorkShop>();

  /**
   * Describe <code>joinWorkshop</code> method here.
   *
   * @param workshop a <code>WorkShop</code> value
   * @return a <code>boolean</code> value
   */
  public boolean joinWorkshop(WorkShop workshop) {
    return workshops.add(workshop);
  }

  /** Describe <code>manufacture</code> method here. */
  public abstract void manufacture();

  /**
   * Describe <code>minWorkTime</code> method here.
   *
   * @return an <code>int</code> value
   */
  public abstract int minWorkTime();
}
