package reflection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Collecting {
  private Collecting() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    Map<String, String> map = new HashMap<>();
    map.put("1", "a");
    map.put("2", "b");
    map.put("3", "c");
    map.put("4", "d");
    reflectionCollections(map);
    reflectionCollections(map.keySet());
    reflectionCollections(map.values());
    List<String> list = new ArrayList<>();
    list.add("10");
    list.add("20");
    list.add("30");
    list.add("40");
    reflectionCollections(list);
    reflectionCollections("this is a string");
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  private static void reflectionCollections(Object ref) {
    // check is collection
    if (ref instanceof Collection) {
      System.out.println("A collection: " + ref.getClass());
      @SuppressWarnings("unchecked")
      Collection<? extends Object> coll = (Collection<? extends Object>)ref;
      coll.forEach(obj -> System.out.println(obj.getClass()));
    } else
      System.out.println("Not a collection: " + ref.getClass().getName());
  }
}
