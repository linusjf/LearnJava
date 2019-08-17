package javapuzzles;

import java.util.IdentityHashMap;
import java.util.Map;

public enum NameGame {
  ;

  public static void main(String... args) {
    Map<String, String> m = new IdentityHashMap<>();
    m.put("Mickey", "Mouse");
    m.put("Mickey", "Mantle");
    System.out.println(m.size());
    altMain(args);
  }

  public static void altMain(String... args) {
    Map<String, String> m = new IdentityHashMap<>();
    m.put("Mickey", "Mouse");
    m.put(new String("Mic" + "key"), "Mantle");
    System.out.println(m.size());
  }
}
