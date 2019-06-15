package design.composite;

/**
 * Describe class <code>Employee</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public abstract class Employee implements Worker {
  protected long employeeId;
  protected String employeeName;
  protected String designation;
  protected Department department;

  /**
   * Creates a new <code>Employee</code> instance.
   *
   * @param employeeId a <code>long</code> value
   * @param employeeName a <code>String</code> value
   * @param designation a <code>String</code> value
   * @param department a <code>Department</code> value
   */
  public Employee(long employeeId, String employeeName, String designation, Department department) {
    super();
    this.employeeId = employeeId;
    this.employeeName = employeeName;
    this.designation = designation;
    this.department = department;
  }

  /**
   * Describe <code>getEmployeeId</code> method here.
   *
   * @return a <code>long</code> value
   */
  public long getEmployeeId() {
    return employeeId;
  }

  /**
   * Describe <code>setEmployeeId</code> method here.
   *
   * @param employeeId a <code>long</code> value
   */
  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }

  /**
   * Describe <code>getEmployeeName</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getEmployeeName() {
    return employeeName;
  }

  /**
   * Describe <code>setEmployeeName</code> method here.
   *
   * @param employeeName a <code>String</code> value
   */
  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  /**
   * Describe <code>getDesignation</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getDesignation() {
    return designation;
  }

  /**
   * Describe <code>setDesignation</code> method here.
   *
   * @param designation a <code>String</code> value
   */
  public void setDesignation(String designation) {
    this.designation = designation;
  }

  /**
   * Describe <code>getDepartment</code> method here.
   *
   * @return a <code>Department</code> value
   */
  public Department getDepartment() {
    return department;
  }

  /**
   * Describe <code>setDepartment</code> method here.
   *
   * @param department a <code>Department</code> value
   */
  public void setDepartment(Department department) {
    this.department = department;
  }

  /**
   * Describe <code>teamSize</code> method here.
   *
   * @return an <code>int</code> value
   */
  public abstract int teamSize();

  /**
   * Describe <code>fullDetails</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String fullDetails() {
    StringBuilder builder = new StringBuilder();
    builder.append("Employee [")
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

  /**
   * Describe <code>shortDetails</code> method here.
   *
   * @return a <code>String</code> value
   */
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
