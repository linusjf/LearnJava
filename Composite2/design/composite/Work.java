package design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe class <code>Work</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Work {
  private Calculator workType;
  private List<String> work = new ArrayList<String>();

  /**
   * Creates a new <code>Work</code> instance.
   *
   * @param workType a <code>Calculator</code> value
   */
  public Work(Calculator workType, List<String> work) {
    super();
    this.workType = workType;
    this.work = work;
  }

  /**
   * Describe <code>getWorkType</code> method here.
   *
   * @return a <code>Calculator</code> value
   */
  public Calculator getWorkType() {
    return workType;
  }

  /**
   * Describe <code>setWorkType</code> method here.
   *
   * @param workType a <code>Calculator</code> value
   */
  public void setWorkType(Calculator workType) {
    this.workType = workType;
  }
  /**
   * Describe <code>getWork</code> method here.
   *
   * @return a <code>List</code> object
   */
  public List<String> getWork() {
    return work;
  }

  /**
   * Describe <code>setWork</code> method here.
   *
   */
  public void setWork(List<String> work) {
    this.work = work;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Work [workType=").append(workType).append(", work=").append(work).append("]");
    return builder.toString();
  }
}
