package design.composite;

public abstract class Employee implements Worker {

  protected long employeeId;
  protected String employeeName;
  protected String designation;
  protected Department department;

  public Employee(long employeeId, String employeeName, String designation, Department department) {
    super();
    this.employeeId = employeeId;
    this.employeeName = employeeName;
    this.designation = designation;
    this.department = department;
  }

  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public abstract int teamSize();

  public String fullDetails() {
    StringBuilder builder = new StringBuilder();
    builder
        .append("Employee [")
        .append(employeeId)
        .append(", ")
        .append(employeeName)
        .append(", ")
        .append(designation)
        .append(", ")
        .append(department)
        .append(", Team=")
        .append(teamSize())
        .append("]");
    return builder.toString();
  }

  public String shortDetails() {
    StringBuilder builder = new StringBuilder();
    builder.append("'").append(employeeName).append("'");
    return builder.toString();
  }

  @Override
  public String toString() {
    return shortDetails();
  }
}
