package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 * Describe class <code>ReportGeneratorImpl</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ReportGeneratorImpl extends UnicastRemoteObject implements ReportGenerator {
  private static final long serialVersionUID = 3107413009881629428L;

  /**
   * Creates a new <code>ReportGeneratorImpl</code> instance.
   *
   * @exception RemoteException if an error occurs
   */
  protected ReportGeneratorImpl() throws RemoteException {
    super();
  }

  @Override
  public String generateDailyReport() throws RemoteException {
    StringBuilder sb = new StringBuilder(250);
    sb.append("********************Location X Daily Report********************")
        .append(System.lineSeparator())
        .append(" Location ID: 012")
        .append(System.lineSeparator())
        .append("Today’s Date: ")
        .append(new Date())
        .append(System.lineSeparator())
        .append(" Total Pizza’s Sell: 112")
        .append(System.lineSeparator())
        .append(" Total Price: $2534")
        .append(System.lineSeparator())
        .append(" Net Profit: $1985")
        .append(System.lineSeparator())
        .append("***************************************************************");
    return sb.toString();
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
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
