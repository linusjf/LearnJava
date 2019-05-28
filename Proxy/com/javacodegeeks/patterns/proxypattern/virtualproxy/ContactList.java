package com.javacodegeeks.patterns.proxypattern.virtualproxy;

import java.util.List;

/**
 * Describe interface <code>ContactList</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface ContactList {
  /**
   * Function to return a list of employees.
   *
   * @return a <code>List</code> of employees.
   */
  List<Employee> getEmployeeList();
}
