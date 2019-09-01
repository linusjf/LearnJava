package io;

import java.io.Serializable;

class Personnel implements Serializable {
  private static final long serialVersionUID = 1L;
  private long payrollNum;
  private String surname;
  private String firstNames;

  public Personnel(long payNum, String sName, String fNames) {
    payrollNum = payNum;
    surname = sName;
    firstNames = fNames;
  }

  public long getPayNum() {
    return payrollNum;
  }

  public String getSurname() {
    return surname;
  }

  public String getFirstNames() {
    return firstNames;
  }

  public void setSurname(String sName) {
    surname = sName;
  }
}
