package design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe class <code>Manager</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Manager extends Employee {
  List<Employee> managingEmployees = new ArrayList<>();

  /**
   * Creates a new <code>Manager</code> instance.
   *
   * @param employeeId a <code>long</code> value
   * @param employeeName a <code>String</code> value
   * @param designation a <code>String</code> value
   * @param department a <code>Department</code> value
   */
  public Manager(
    long employeeId,
    String employeeName,
    String designation,
    Department department
  ) {
    super(employeeId, employeeName, designation, department);
  }

  /**
   * Describe <code>manages</code> method here.
   *
   * @param employee an <code>Employee</code> value
   * @return a <code>boolean</code> value
   */
  public boolean manages(Employee employee) {
    return managingEmployees.add(employee);
  }

  /**
   * Describe <code>stopManaging</code> method here.
   *
   * @param employee an <code>Employee</code> value
   * @return a <code>boolean</code> value
   */
  public boolean stopManaging(Employee employee) {
    return managingEmployees.remove(employee);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public int teamSize() {
    return managingEmployees.stream()
      .mapToInt(employee -> employee.teamSize())
      .sum();
  }

  @SuppressWarnings(
    { "PMD.AvoidInstantiatingObjectsInLoops", "PMD.DataflowAnomalyAnalysis" }
  )
  @Override
  public void assignWork(Employee manager, Work work) {
    System.out.println(
      this + " has been assigned work of '" + work + "' by manager " + manager
    );
    System.out.println();
    System.out.println(
      this + " distributing work '" + work + "' to managed employees.."
    );
    int fromIndex = 0;
    int toIndex = 0;
    int totalWork = work.getWorkSize();
    System.out.println("totalWork = " + totalWork);
    while (toIndex < totalWork) {
      for (Employee employee : managingEmployees) {
        System.out.println("Assigning work from " + employee);
        toIndex = fromIndex + employee.teamSize();
        if (toIndex > totalWork) toIndex = totalWork;
        if (fromIndex == toIndex) return;
        List<String> assignWork = work.getWork(fromIndex, toIndex);
        employee.assignWork(this, new Work(work.getWorkType(), assignWork));
        fromIndex = toIndex;
      }
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void performWork() {
    System.out.println(
      this + " is asking his/her managed employees to perform assigned work"
    );
    System.out.println();
    managingEmployees.stream().forEach(employee -> employee.performWork());
    System.out.println();
    System.out.println(
      this +
        " has completed assigned work with the help of his/her managed employees"
    );
    System.out.println();
  }
}
