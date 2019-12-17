package com.javacodegeeks.patterns.proxypattern.protectionproxy;

/**
 * Describe class <code>Employee</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Employee implements Staff {
  private ReportGeneratorProxy reportGenerator;

  @Override
  public void setReportGenerator(
      ReportGeneratorProxy reportGenerator) {
    this.reportGenerator = reportGenerator;
  }

  @Override
  public boolean isOwner() {
    return false;
  }

  /**
   * Describe <code>generateDailyReport</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String generateDailyReport() {
    if (reportGenerator == null)
      return "";
    return reportGenerator.generateDailyReport();
  }
}
