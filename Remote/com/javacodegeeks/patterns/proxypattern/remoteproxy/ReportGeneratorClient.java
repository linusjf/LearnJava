package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.net.MalformedURLException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

public class ReportGeneratorClient {

  public static void main(String[] args) {
    new ReportGeneratorClient().generateReport();
  }

  public void generateReport() {
    try {
      Registry registry = LocateRegistry.getRegistry(null);
      ReportGenerator stub = (ReportGenerator) registry.lookup("PizzaCoRemoteGenerator");
      //ReportGenerator reportGenerator = (ReportGenerator)Naming.lookup("rmi://127.0.0.1/PizzaCoRemoteGenerator");
      System.out.println(stub.generateDailyReport());
    } catch (RemoteException 
        | NotBoundException e) {
      System.out.println(e.getMessage());
    }
  }
}
