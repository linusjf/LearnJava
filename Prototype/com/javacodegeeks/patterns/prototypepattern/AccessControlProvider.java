package com.javacodegeeks.patterns.prototypepattern;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class AccessControlProvider {
  private static final Logger LOGGER =
      Logger.getLogger(AccessControlProvider.class.getName());

  private static Map<String, AccessControl> map = new HashMap<>();

  static {
    LOGGER.info(
        "Fetching data from external resources and creating access control objects...");
    map.put("USER", new AccessControl("USER", "DO_WORK"));
    map.put("ADMIN", new AccessControl("ADMIN", "ADD/REMOVE USERS"));
    map.put("MANAGER", new AccessControl("MANAGER", "GENERATE/READ REPORTS"));
    map.put("VP", new AccessControl("VP", "MODIFY REPORTS"));
  }

  private AccessControlProvider() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static AccessControl getAccessControlObject(String controlLevel)
      throws CloneNotSupportedException {
    AccessControl ac = map.get(controlLevel);
    return ac == null ? null : ac.clone();
  }
}
