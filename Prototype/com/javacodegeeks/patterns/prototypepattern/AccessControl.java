package com.javacodegeeks.patterns.prototypepattern;

import java.util.logging.Logger;

@SuppressWarnings("checkstyle:noclone")
public class AccessControl implements Prototype {
  private static final Logger LOGGER =
      Logger.getLogger(AccessControl.class.getName());

  private final String controlLevel;

  private String access;

  public AccessControl(String controlLevel, String access) {
    this.controlLevel = controlLevel;
    this.access = access;
  }

  @Override
  public AccessControl clone() throws CloneNotSupportedException {
    try {
      return (AccessControl)super.clone();
    } catch (CloneNotSupportedException e) {
      LOGGER.severe(e.getMessage());
    }
    return null;
  }

  public String getControlLevel() {
    return controlLevel;
  }

  public String getAccess() {
    return access;
  }

  public void setAccess(String access) {
    this.access = access;
  }
}
