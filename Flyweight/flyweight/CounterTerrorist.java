package flyweight;

// CounterTerrorist must have weapon and mission
/**
 * Describe class <code>CounterTerrorist</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
class CounterTerrorist implements Player {
  // Intrinsic Attribute
  @SuppressWarnings("checkstyle:membername") private final String TASK; // NOPMD

  // Extrinsic Attribute
  private String weapon;

  /** Creates a new <code>CounterTerrorist</code> instance. */
  CounterTerrorist() {
    TASK = "DEFUSE BOMB";
  }

  /**
   * Describe <code>assignWeapon</code> method here.
   *
   * @param weapon a <code>String</code> value
   */
  @SuppressWarnings("checkstyle:hiddenfield")
  @Override
  public void assignWeapon(String weapon) {
    this.weapon = weapon;
  }

  /** Describe <code>mission</code> method here. */
  @Override
  public void mission() {
    System.out.println("Counter Terrorist with weapon " + weapon + "|"
        + " Task is " + TASK);
  }
}
