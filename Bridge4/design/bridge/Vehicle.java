package design.bridge;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
  // assembly line for the workshops
  protected List<WorkShop> workshops = new ArrayList<WorkShop>();

  public Vehicle() {
    super();
  }

  public boolean joinWorkshop(WorkShop workshop) {
    return workshops.add(workshop);
  }

  public abstract void manufacture();

  public abstract int minWorkTime();
}

