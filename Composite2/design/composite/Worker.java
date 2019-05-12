package design.composite;

/**
 * Describe interface <code>Worker</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface Worker {
  /**
   * Describe <code>assignWork</code> method here.
   *
   * @param manager an <code>Employee</code> value
   * @param work a <code>Work</code> value
   */
  void assignWork(Employee manager, Work work);

  /** Describe <code>performWork</code> method here. */
  void performWork();
}
