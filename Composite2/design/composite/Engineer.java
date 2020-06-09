package design.composite;

import java.util.ArrayList;
import java.util.List;
/**
 * Describe class <code>Engineer</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Engineer extends Employee {
  private final List<Work> works = new ArrayList<>();

  /**
   * Creates a new <code>Engineer</code> instance.
   *
   * @param employeeId a <code>long</code> value
   * @param employeeName a <code>String</code> value
   * @param designation a <code>String</code> value
   * @param department a <code>Department</code> value
   */
  public Engineer(long employeeId, String employeeName, String designation, Department department) {
    super(employeeId, employeeName, designation, department);
  }

  @Override
  public int teamSize() {
    return 1;
  }

  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void assignWork(Employee manager, Work work) {
    this.works.add(work);
    System.out.println(this + " has assigned work of \'" + work + "\' by manager " + manager);
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  @Override
  public void performWork() {
    System.out.println(this + " is performing work of \'" + works + "\'");
    works.stream().forEach(work -> {
      work.getWork().stream().forEach(value -> {
        Calculator calculator = work.getWorkType();
        System.out.println(this + " has result of work of \'" + work + "\' as : " + calculator.calculate(value));
      });
    });
    works.clear();
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Engineer(works=" + this.works + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Engineer)) return false;
    Engineer other = (Engineer) o;
    if (!other.canEqual((Object) this)) return false;
    if (!super.equals(o)) return false;
    Object this$works = this.works;
    Object other$works = other.works;
    if (this$works == null ? other$works != null : !this$works.equals(other$works)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Engineer;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = super.hashCode();
    Object $works = this.works;
    result = result * PRIME + ($works == null ? 43 : $works.hashCode());
    return result;
  }
}
