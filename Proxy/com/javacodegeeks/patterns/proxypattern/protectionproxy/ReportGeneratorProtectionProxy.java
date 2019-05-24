package com.javacodegeeks.patterns.proxypattern.protectionproxy;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.ReportGenerator;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class ReportGeneratorProtectionProxy implements ReportGeneratorProxy {
  ReportGenerator reportGenerator;
  Staff staff;

  public ReportGeneratorProtectionProxy(Staff staff) {
    this.staff = staff;
  }

  @Override
  public String generateDailyReport() {
    if (staff.isOwner()) {
      ReportGenerator reportGenerator = null;
      try {
        reportGenerator = (ReportGenerator) Naming.lookup("rmi://127.0.0.1/PizzaCoRemoteGenerator");
        return reportGenerator.generateDailyReport();
      } catch (MalformedURLException 
          | RemoteException
          | NotBoundException e) {
        System.err.println(e.getMessage());
      }
      return "";
    } else {
      return "Not Authorized to view report.";
    }
  }
}
