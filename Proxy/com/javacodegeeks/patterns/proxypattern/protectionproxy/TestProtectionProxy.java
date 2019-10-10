package com.javacodegeeks.patterns.proxypattern.protectionproxy;

public enum TestProtectionProxy {
  ;
  /**
   * Main function.
   *
   * @param args String[] array of String arguments
   */
  public static void main(String[] args) {
    Owner owner = new Owner();
    ReportGeneratorProxy reportGenerator =
        new ReportGeneratorProtectionProxy(owner);
    owner.setReportGenerator(reportGenerator);
    Employee employee = new Employee();
    reportGenerator = new ReportGeneratorProtectionProxy(employee);
    employee.setReportGenerator(reportGenerator);
    System.out.println("For owner:");
    System.out.println(owner.generateDailyReport());
    System.out.println("For employee:");
    System.out.println(employee.generateDailyReport());
  }
}
