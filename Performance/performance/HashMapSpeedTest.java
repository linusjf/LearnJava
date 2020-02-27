package performance;

import com.google.common.collect.Maps;
import gnu.trove.map.hash.THashMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.collections.FastTreeMap;
import org.springframework.util.StopWatch;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HashMapSpeedTest {

  public static final int SIZE = 1_000_000;
  private Set<String> objects;
  private StopWatch stopWatch;

  @BeforeClass
  public void setUp() {
    System.out.println("creating " + SIZE + " objects");
    System.out.println("Implementation;get();put()");
    objects = getObjects();
    stopWatch = new StopWatch();
  }

  @DataProvider
  public Object[][] mapProvider() {
    return new Object[][] {
        {new HashMap<String, Object>(SIZE, 1f), "SIZE,1f"},
        {new HashMap<String, Object>(SIZE, 10f), "SIZE,10f"},
        {new HashMap<String, Object>(SIZE, 0.3f), "SIZE,0.3f"},
        {new HashMap<String, Object>(SIZE, 0.1f), "SIZE,0.1f"},
        {new HashMap<String, Object>(), ""},
        {new HashMap<String, Object>(SIZE), "SIZE"},
        {new THashMap<String, Object>(), ""},
        {new THashMap<String, Object>(SIZE), "SIZE"},
        {new THashMap<String, Object>(SIZE, 1f), "SIZE,1f"},
        {new FastHashMap(), ""},
        {new FastHashMap(SIZE), "SIZE"},
        {new TreeMap<String, Object>(), ""},
        {Maps.newHashMap(), ""},
        {new FastTreeMap(), ""},
    };
  }

  @Test(dataProvider = "mapProvider", singleThreaded = true)
  public void test(Map<String, Object> map, String typeExtension) {
    String type = map.getClass().getName() + "(" + typeExtension + ")";

    stopWatch.start(type + "put()");
    for (String o: objects)
      map.put(o, o);
    stopWatch.stop();
    long putTime = stopWatch.getLastTaskTimeMillis();

    stopWatch.start(type + "get()");
    for (String o: objects)
      map.get(o);
    stopWatch.stop();
    long getTime = stopWatch.getLastTaskTimeMillis();
    System.out.println(type + ";" + getTime + ";" + putTime);
    map.clear();
    System.gc();
  }

  @AfterClass
  public void tearDown() throws Exception {
    System.out.println(stopWatch.prettyPrint());
  }

  private Set<String> getObjects() {
    Set<String> objects = new HashSet<>();
    for (int i = 0; i < SIZE; i++)
      objects.add("" + i);
    return objects;
  }
}
