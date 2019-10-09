package com.javacodegeeks.patterns.proxypattern.virtualproxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe class <code>ContactListImpl</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ContactListImpl implements ContactList {

  @Override
  public List<Employee> getEmployeeList() {
    return getEmpList();
  }

  @SuppressWarnings("checkstyle:magicnumber")
  private static List<Employee> getEmpList() {
    List<Employee> empList = new ArrayList<>(5);
    empList.add(new Employee("Employee A", 2565.55, "SE"));
    empList.add(new Employee("Employee B", 22_574, "Manager"));
    empList.add(new Employee("Employee C", 3256.77, "SSE"));
    empList.add(new Employee("Employee D", 4875.54, "SSE"));
    empList.add(new Employee("Employee E", 2847.01, "SE"));
    return empList;
  }
}
