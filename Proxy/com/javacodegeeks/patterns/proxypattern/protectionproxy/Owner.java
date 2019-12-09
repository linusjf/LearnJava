package com.javacodegeeks.patterns.proxypattern.protectionproxy;

/**
 * Describe class <code>Owner</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.FinalFieldCouldBeStatic")
public class Owner implements Staff {
  private final boolean owning = true;

  private ReportGeneratorProxy reportGenerator;

  @Override
  public void setReportGenerator(ReportGeneratorProxy reportGenerator) {
    this.reportGenerator = reportGenerator;
  }

  @Override
  public boolean isOwner() {
    return owning;
  }

  /**
   * Describe <code>generateDailyReport</code> method here.
   *
   * @return a <code>String</code> value.
   * Returns empty string if reportGenerator is null.
   */
  public String generateDailyReport() {
    if (reportGenerator == null)
      return "";
    return reportGenerator.generateDailyReport();
  }
}
