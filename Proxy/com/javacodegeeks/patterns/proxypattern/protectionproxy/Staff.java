package com.javacodegeeks.patterns.proxypattern.protectionproxy;

/**
 * Describe interface <code>Staff</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface Staff {
  /**
   * Describe <code>isOwner</code> method here.
   *
   * @return a <code>boolean</code> value
   */
  boolean isOwner();

  /**
   * Describe <code>setReportGenerator</code> method here.
   *
   * @param reportGenerator a <code>ReportGeneratorProxy</code> value
   */
  void setReportGenerator(
      ReportGeneratorProxy reportGenerator);
}
