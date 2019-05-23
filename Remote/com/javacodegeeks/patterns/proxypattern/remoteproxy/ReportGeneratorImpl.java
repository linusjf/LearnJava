package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ReportGeneratorImpl extends UnicastRemoteObject implements ReportGenerator {

  private static final long serialVersionUID = 3107413009881629428L;

  protected ReportGeneratorImpl() throws RemoteException {}

  @Override
  public String generateDailyReport() throws RemoteException {
    StringBuilder sb = new StringBuilder();
    sb.append("********************Location X Daily Report********************");
    sb.append("\\n Location ID: 012");
    sb.append("\\n Today’s Date: " + new Date());
    sb.append("\\n Total Pizza’s Sell: 112");
    sb.append("\\n Total Price: $2534");
    sb.append("\\n Net Profit: $1985");
    sb.append("\\n*****************************" + "**********************************");
    return sb.toString();
  }

  public static void main(String[] args) {
    try {
      ReportGeneratorImpl reportGenerator = new ReportGeneratorImpl();
      // Bind the remote object's stub in the registry
      Registry registry = LocateRegistry.getRegistry();
      registry.bind("PizzaCoRemoteGenerator", reportGenerator);
    } catch (RemoteException | AlreadyBoundException e) {
      System.out.println(e.getMessage());
    }
  }
}
