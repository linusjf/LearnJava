package remote;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public final class Bank1Client {
  private static final String HOST = "localhost";

  private Bank1Client() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.DoNotCallSystemExit")
  public static void main(String[] args) {
    try {
      // Obtain a reference to the object from the
      // registry and typecast it into the appropriate
      // type…
      Bank1 temp = (Bank1) Naming.lookup("rmi://" + HOST + "/Accounts");
      printBankAccounts(temp);
    } catch (ConnectException conEx) {
      System.out.println("Unable to connect to server!");
      System.exit(1);
    } catch (RemoteException | NotBoundException | MalformedURLException ex) {
      System.err.println(ex);
      System.exit(1);
    }
  }

  private static void printBankAccounts(Bank1 bank) throws RemoteException {
    List<Account> acctDetails = bank.getBankAccounts();

    // simply display all acct details…
    for (Account acct : acctDetails) {
      // now invoke methods of account object
      // to display its details…
      System.out.println("\naccount number: " + acct.getAcctNum());
      System.out.println("name: " + acct.getName());
      System.out.println("balance: " + acct.getBalance());
    }
  }
}
