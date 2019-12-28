package javapuzzles;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum NullFunctionValues {
  ;

  public static void main(String... args) {
    Map<String, String> map = new ConcurrentHashMap<>();
    for (int i = 0; i < 42; i++)
      map.merge("Batman", "Hulk", (s1, s2) -> map.get(s1));

    System.out.println(map);
    for (int i = 0; i < 37; i++)
      map.merge("Batman", "Hulk", (s1, s2) -> map.get(s1));

    System.out.println(map);
    for (int i = 0; i < 37; i++)
      map.merge("Batman", "Hulk", (s1, s2) -> s2);

    System.out.println(map);
    for (int i = 0; i < 42; i++)
      map.merge("Batman", "Hulk", (s1, s2) -> s2);
    System.out.println(map);
    
    for (int i = 0; i < 42; i++) {
      map.merge("Batman", "Hulk", (oldValue, newValue) -> newValue);
      if (i % 2 == 0)
      map.merge("Batman", "The Thing", (oldValue, newValue) -> newValue);
    System.out.println(i + ": " + map);
    }
  }
}
