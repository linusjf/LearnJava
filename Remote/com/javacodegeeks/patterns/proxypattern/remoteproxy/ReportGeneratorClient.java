package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReportGeneratorClient {

  public static void main(String[] args) {
    new ReportGeneratorClient().generateReport();
  }

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
