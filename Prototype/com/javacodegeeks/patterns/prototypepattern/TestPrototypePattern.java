package com.javacodegeeks.patterns.prototypepattern;

import java.util.logging.Logger;

public enum TestPrototypePattern {
  ;

  private static final Logger LOGGER =
      Logger.getLogger(TestPrototypePattern.class.getName());

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] args) {
    try {
      AccessControl userAccessControl =
          AccessControlProvider.getAccessControlObject("USER");
      User user = new User("User A", "USER Level", userAccessControl);
      System.out.println("************************************");
      System.out.println(user);
      userAccessControl = AccessControlProvider.getAccessControlObject("USER");
      user = new User("User B", "USER Level", userAccessControl);
      System.out.println("Changing access control of: " + user.getUserName());
      user.setAccess("READ REPORTS");
      System.out.println(user);
      System.out.println("************************************");
      AccessControl managerAccessControl =
          AccessControlProvider.getAccessControlObject("MANAGER");
      user = new User("User C", "MANAGER Level", managerAccessControl);
      System.out.println(user);
    } catch (CloneNotSupportedException cnse) {
      LOGGER.severe(cnse.getMessage());
    }
  }
}
