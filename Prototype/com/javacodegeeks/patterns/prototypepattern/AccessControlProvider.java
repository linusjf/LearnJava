package com.javacodegeeks.patterns.prototypepattern;

import java.util.HashMap;
import java.util.Map;

public final class AccessControlProvider {
  private static Map<String, AccessControl> map = new HashMap<>();

  static {
    System.out.println(
        "Fetching data from external resources and creating access control objects...");
    map.put("USER", new AccessControl("USER", "DO_WORK"));
    map.put("ADMIN", new AccessControl("ADMIN", "ADD/REMOVE USERS"));
    map.put("MANAGER", new AccessControl("MANAGER", "GENERATE/READ REPORTS"));
    map.put("VP", new AccessControl("VP", "MODIFY REPORTS"));
  }

  public static AccessControl getAccessControlObject(String controlLevel)
      throws CloneNotSupportedException {
    AccessControl ac = null;
    ac = map.get(controlLevel);
    return ac != null ? ac.clone():null;
  }

  private AccessControlProvider() {
    throw new IllegalStateException("Private constructor");
  }
}
