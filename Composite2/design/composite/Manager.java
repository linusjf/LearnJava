package design.composite;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
  List<Employee> managingEmployees = new ArrayList<Employee>();

  public Manager(long employeeId, String employeeName, String designation, Department department) {
    super(employeeId, employeeName, designation, department);
  }

  public boolean manages(Employee employee) {
    return managingEmployees.add(employee);
  }

  public boolean stopManaging(Employee employee) {
    return managingEmployees.remove(employee);
  }

  @Override
  public int teamSize() {
    return managingEmployees.stream().mapToInt(employee -> employee.teamSize()).sum();
    /*int sum = 0;
    for (Employee employee: managingEmployees)
      sum += employee.teamSize();
    return sum;*/
  }

  @Override
  public void assignWork(Employee manager, Work work) {
    System.out.println(this + " has been assigned work of '" + work + "' by manager " + manager);
    System.out.println();
    System.out.println(this + " distributing work '" + work + "' to managed employees..");
    int fromIndex = 0;
    int toIndex = 0;
    int totalWork = work.getWork().size();
    List<String> assignWork = null;
    while (toIndex < totalWork) {
      for (Employee employee : managingEmployees) {
        System.out.println("Assigning to " + employee);
        int size = employee.teamSize();
        toIndex = fromIndex + size;
        assignWork = work.getWork().subList(fromIndex, toIndex);
        if (assignWork.isEmpty()) {
          return;
        }
        employee.assignWork(this, new Work(work.getWorkType(), assignWork));
        fromIndex = toIndex;
      }
      break;
    }
  }

  @Override
  public void performWork() {
    System.out.println(this + " is asking his/her managed employees to perform assigned work");
    System.out.println();
    managingEmployees.stream().forEach(employee -> employee.performWork());
    /*for (Employee employee: managingEmployees)
    employee.performWork();*/
    System.out.println();
    System.out.println(
        this + " has completed assigned work with the help of his/her managed employees");
    System.out.println();
  }
}
