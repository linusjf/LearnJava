package networking;

import java.io.Serializable;

class Personnel implements Serializable {
  private static final long serialVersionUID = 1L;
  private final long payrollNum;
  private String surname;
  private final String firstNames;

  Personnel(long payNum,
            String surName,
            String firstNames) {
    payrollNum = payNum;
    surname = surName;
    this.firstNames = firstNames;
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

  public void setSurname(String surName) {
    surname = surName;
  }
}
