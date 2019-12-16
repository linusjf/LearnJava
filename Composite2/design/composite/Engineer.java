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
  public void assignWork(Employee manager, Work work) {
    this.works.add(work);
    System.out.println(this + " has assigned work of '" + work + "' by manager " + manager);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void performWork() {
    System.out.println(this + " is performing work of '" + works + "'");
    works.stream().forEach(work -> {
      work.getWork().stream().forEach(value -> {
        Calculator calculator = work.getWorkType();
        System.out.println(
            this + " has result of work of '" + work + "' as : " + calculator.calculate(value));
      });
    });
    works.clear();
  }
}
