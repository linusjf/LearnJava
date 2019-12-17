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
  ReportGenerator stub;

  public ReportGeneratorClient()
      throws RemoteException, NotBoundException {
    Registry registry = LocateRegistry.getRegistry(null);
    stub = (ReportGenerator)registry.lookup(
        "PizzaCoRemoteGenerator");
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    try {
      new ReportGeneratorClient().generateReport();
    } catch (RemoteException | NotBoundException e) {
      System.out.println(e.getMessage());
    }
  }

  /** Describe <code>generateReport</code> method here. */
  public void generateReport() {
    try {
      System.out.println(stub.generateDailyReport());
    } catch (RemoteException e) {
      System.out.println(e.getMessage());
    }
  }
}
