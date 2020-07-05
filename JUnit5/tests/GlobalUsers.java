package tests;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class GlobalUsers {

  private static Map<Integer, String> users = new HashMap<>();

  private GlobalUsers() {
    throw new IllegalStateException("Private constructor for class "
                                    + GlobalUsers.class);
  }

  public static String get(int id) {
    return users.get(id);
  }

  public static void add(int id, String user) {
    users.put(id, user);
  }

  public static void update(int id, String user) {
    users.put(id, user);
  }

  public static void remove(int id) {
    users.remove(id);
  }

  public static void clear() {
    users.clear();
  }

  public static Collection<String> getUsers() {
    return users.values();
  }

  public static void setUsers(Map<Integer, String> value) {
    users = value;
  }
}
