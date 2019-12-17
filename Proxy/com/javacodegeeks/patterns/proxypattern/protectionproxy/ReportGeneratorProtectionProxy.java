package com.javacodegeeks.patterns.proxypattern.protectionproxy;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.ReportGenerator;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Describe class <code>ReportGeneratorProtectionProxy</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ReportGeneratorProtectionProxy
    implements ReportGeneratorProxy {
  Staff staff;

  ReportGenerator reportGenerator;

  /**
   * Creates a new <code>ReportGeneratorProtectionProxy</code> instance.
   *
   * @param staff a <code>Staff</code> value
   */
  public ReportGeneratorProtectionProxy(Staff staff) {
    this.staff = staff;
  }

  private ReportGenerator getReportGenerator()
      throws NotBoundException, MalformedURLException,
             RemoteException {
    if (reportGenerator == null)
      reportGenerator = (ReportGenerator)Naming.lookup(
          "rmi://127.0.0.1/PizzaCoRemoteGenerator");
    return reportGenerator;
  }

  @Override
  public String generateDailyReport() {
    if (staff.isOwner()) {
      try {
        getReportGenerator();
        return reportGenerator.generateDailyReport();
      } catch (MalformedURLException | RemoteException
               | NotBoundException e) {
        System.err.println(e.getMessage());
      }
      return "";
    } else
      return "Not Authorized to view report.";
  }
}
