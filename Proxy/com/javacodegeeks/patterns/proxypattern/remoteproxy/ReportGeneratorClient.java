package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Describe class <code>ReportGeneratorClient</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ReportGeneratorClient {
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    new ReportGeneratorClient().generateReport();
  }

  /** Describe <code>generateReport</code> method here. */
  public void generateReport() {
    try {
      Registry registry = LocateRegistry.getRegistry(null);
      ReportGenerator stub = (ReportGenerator) registry.lookup("PizzaCoRemoteGenerator");
      System.out.println(stub.generateDailyReport());
    } catch (RemoteException | NotBoundException e) {
      System.out.println(e.getMessage());
    }
  }
}
