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
      map.merge("Batman", "Hulk", (s1, s2) -> s1);

    System.out.println(map);
    for (int i = 0; i < 42; i++)
      map.merge("Batman", "Hulk", (s1, s2) -> s1);
    System.out.println(map);
  }
}
