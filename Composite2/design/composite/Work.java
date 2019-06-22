package design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe class <code>Work</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
public class Work {
  private Calculator workType;
  private List<String> jobs = new ArrayList<>();

  /**
   * Creates a new <code>Work</code> instance.
   *
   * @param workType a <code>Calculator</code> value
   * @param work list of tasks
   */
  public Work(Calculator workType, List<String> work) {
    super();
    this.workType = workType;
    this.jobs = work;
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
    return jobs;
  }

  /**
   * Describe <code>setWork</code> method here.
   *
   * @param work list of tasks.
   */
  public void setWork(List<String> work) {
    this.jobs = work;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(46);
    builder.append("Work [workType=")
        .append(workType)
        .append(", work=")
        .append(jobs)
        .append(']');
    return builder.toString();
  }
}
