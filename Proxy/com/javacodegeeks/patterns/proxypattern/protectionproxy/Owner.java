package com.javacodegeeks.patterns.proxypattern.protectionproxy;

/**
 * Describe class <code>Owner</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Owner implements Staff {

  private boolean isOwner = true;

  private ReportGeneratorProxy reportGenerator;

  @Override
  public void setReportGenerator(ReportGeneratorProxy reportGenerator) {
    this.reportGenerator = reportGenerator;
  }

  @Override
  public boolean isOwner() {
    return isOwner;
  }

  /**
   * Describe <code>generateDailyReport</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String generateDailyReport() {
    return reportGenerator.generateDailyReport();
  }
}
