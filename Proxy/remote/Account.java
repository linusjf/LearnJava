package remote;

import java.io.Serializable;

public class Account implements Serializable {
  private static final long serialVersionUID = 1L;

  // Instance variables…
  private final int acctNum;
  private final String surname;
  private final String firstNames;
  private double balance;

  // Constructor…
  public Account(int acctNo,
                 String sname,
                 String fnames,
                 double bal) {
    acctNum = acctNo;
    surname = sname;
    firstNames = fnames;
    balance = bal;
  }

  // Methods…
  public int getAcctNum() {
    return acctNum;
  }

  public String getName() {
    return firstNames + " " + surname;
  }

  public double getBalance() {
    return balance;
  }

  public double withdraw(double amount) {
    if (amount > 0 && amount <= balance)
      return amount;
    else
      return 0;
  }

  public void deposit(double amount) {
    if (amount > 0)
      balance += amount;
  }
}
