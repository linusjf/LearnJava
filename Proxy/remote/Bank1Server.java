package remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public final class Bank1Server {
  private static final String HOST = "localhost";

  private Bank1Server() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    // Create an initialised array of four Account
    // objects
    // clang-format off
    Account[] account = {
      new Account(111_111, "Smith", "Fred James", 112.58),
      new Account(222_222, "Jones", "Sally", 507.85),
      new Account(234_567, "White", "Mary Jane", 2345.00),
      new Account(666_666, "Satan", "Beelzebub", 666.00),
    };

    // clang-format on
    List<Account> acctDetails = new ArrayList<>();

    // Insert the Account objects into the ArrayList …
    for (Account acct: account)
      acctDetails.add(acct);
    try {
      // Create an implementation object, passing the
      // above ArrayList to the constructor…
      Bank1Impl temp = new Bank1Impl(acctDetails);

      // Save the object's name in a String …
      String rmiObjectName = "rmi://" + HOST + "/Accounts";

      // (Could omit host name, since 'localhost' would be
      // assumed by default.)
      // Bind the object's name to its reference…
      Naming.rebind(rmiObjectName, temp);
      System.out.println("Binding complete…\n");
    } catch (RemoteException re) {
      System.err.println(re);
    } catch (MalformedURLException re) {
      System.err.println("Malformed url: " + re);
    }
  }
}
