package com.javacodegeeks.patterns.proxypattern.remoteproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Describe interface <code>ReportGenerator</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface ReportGenerator extends Remote {

  /**
   * Describe <code>generateDailyReport</code> method here.
   *
   * @return a <code>String</code> value
   * @exception RemoteException if an error occurs
   */
  String generateDailyReport() throws RemoteException;
}
