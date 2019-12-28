package javapuzzles;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum NullFunctionValues {
  ;

  private static final String HULK = "Hulk";
  private static final String BATMAN = "Batman";
  private static final String THING = "The Thing";

  public static void main(String... args) {
    Map<String, String> map = new ConcurrentHashMap<>();

    System.out.println("Returning map.get(s1):");
    for (int i = 0; i < 42; i++)
      map.merge(BATMAN, HULK, (s1, s2) -> map.get(s1));

    System.out.println(map);
    for (int i = 0; i < 37; i++)
      map.merge(BATMAN, HULK, (s1, s2) -> map.get(s1));

    System.out.println(map);

    map.clear();
    System.out.println("Returning null directly:");
    for (int i = 0; i < 42; i++)
      map.merge(BATMAN, HULK, (s1, s2) -> null);

    System.out.println(map);
    for (int i = 0; i < 37; i++)
      map.merge(BATMAN, HULK, (s1, s2) -> null);

    System.out.println(map);

    map.clear();
    System.out.println("Returning s2 directly:");
    for (int i = 0; i < 42; i++)
      map.merge(BATMAN, HULK, (s1, s2) -> s2);
    System.out.println(map);
    for (int i = 0; i < 37; i++)
      map.merge(BATMAN, HULK, (s1, s2) -> s2);

    System.out.println(map);

    map.clear();
    System.out.println("Alternating values:");
    for (int i = 0; i < 42; i++) {
      if (i % 2 == 0)
        map.merge(BATMAN, THING, (oldValue, newValue) -> newValue);
      else
        map.merge(BATMAN, HULK, (oldValue, newValue) -> newValue);
      System.out.println(i + ": " + map);
    }
  }
}
