package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Bank1Impl extends UnicastRemoteObject implements Bank1 {
  private static final long serialVersionUID = 1L;

  // Declare the ArrayList that will hold Account
  // objects…
  private final List<Account> acctInfo;

  // The constructor must be supplied with an ArrayList
  // of Account objects…
  public Bank1Impl(List<Account> acctVals) throws RemoteException {
    super();
    acctInfo = acctVals;
  }

  // Definition for the single interface method…
  @Override
  public List<Account> getBankAccounts() throws RemoteException {
    return acctInfo;
  }
}
