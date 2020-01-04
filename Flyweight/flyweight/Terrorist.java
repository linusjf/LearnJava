package flyweight;

/**
 * Describe class <code>Terrorist</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
class Terrorist implements Player {
  // Intrinsic Attribute
  // clang-format OFF
  @SuppressWarnings("checkstyle:membername")
  private final String TASK;  // NOPMD

  // clang-format ON
  // Extrinsic Attribute
  private String weapon;

  /** Creates a new <code>Terrorist</code> instance. */
  Terrorist() {
    TASK = "PLANT A BOMB";
  }

  /**
   * Describe <code>assignWeapon</code> method here.
   *
   * @param weapon a <code>String</code> value
   */
  @SuppressWarnings("checkstyle:hiddenfield")
  @Override
  public void assignWeapon(String weapon) {
    // Assign a weapon
    this.weapon = weapon;
  }

  /** Describe <code>mission</code> method here. */
  @Override
  public void mission() {
    // Work on the Mission
    System.out.println("Terrorist with weapon " + weapon + "|"
                       + " Task is " + TASK);
  }
}
