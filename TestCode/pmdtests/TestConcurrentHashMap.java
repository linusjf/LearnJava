package pmdtests;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum TestConcurrentHashMap {
  ;

  public void testMap() {
    Map<String, Object> map1 = new HashMap<>();
    // fine for single-threaded access
    Map<String, Object> map2 = new ConcurrentHashMap<>();
    // preferred for use with multiple threads

    // the following case will be ignored by this rule
    Map<String, Object> map3 =
        methodThatReturnMap();  // might be OK, if the returned map is already thread-safe
  }

  private Map<String, Object> methodThatReturnMap() {
    return new HashMap<String, Object>();
  }
}
