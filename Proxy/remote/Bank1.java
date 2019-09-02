package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Bank1 extends Remote {
  List<Account> getBankAccounts() throws RemoteException;
}
