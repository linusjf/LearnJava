package com.javacodegeeks.patterns.proxypattern.virtualproxy;

/**
 * Describe class <code>Employee</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Employee {
  private final String employeeName;
  private final double employeeSalary;
  private final String employeeDesignation;

  /**
   * Creates a new <code>Employee</code> instance.
   *
   * @param employeeName a <code>String</code> value
   * @param employeeSalary a <code>double</code> value
   * @param employeeDesignation a <code>String</code> value
   */
  public Employee(String employeeName, double employeeSalary, String employeeDesignation) {
    this.employeeName = employeeName;
    this.employeeSalary = employeeSalary;
    this.employeeDesignation = employeeDesignation;
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
   * Describe <code>getEmployeeSalary</code> method here.
   *
   * @return a <code>double</code> value
   */
  public double getEmployeeSalary() {
    return employeeSalary;
  }

  /**
   * Describe <code>getEmployeeDesignation</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getEmployeeDesignation() {
    return employeeDesignation;
  }

  /**
   * Describe <code>toString</code> method here.
   *
   * @return a <code>String</code> value
   */
  @Override
  public String toString() {
    return "Employee Name: "
        + employeeName
        + ", EmployeeDesignation: "
        + employeeDesignation
        + ", Employee Salary: "
        + employeeSalary;
  }
}
