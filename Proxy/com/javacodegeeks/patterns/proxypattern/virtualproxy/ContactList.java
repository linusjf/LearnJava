package com.javacodegeeks.patterns.proxypattern.virtualproxy;

import java.util.List;

/**
 * Describe interface <code>ContactList</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface ContactList {

  /** @return a <code>List</code> of employees. */
  List<Employee> getEmployeeList();
}
