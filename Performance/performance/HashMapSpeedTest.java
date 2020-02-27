package performance;

import com.google.common.collect.Maps;
import gnu.trove.map.hash.THashMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;
import javolution.util.FastMap;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.collections.FastTreeMap;
import org.springframework.util.StopWatch;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HashMapSpeedTest {

  private static final Logger LOGGER =
      Logger.getLogger(HashMapSpeedTest.class.getName());

  public static final int SIZE = 1_000_000;
  private Set<String> objects;
  private StopWatch stopWatch;

  @BeforeClass
  public void setUp() {
    LOGGER.info(() -> "creating " + SIZE + " objects");
    LOGGER.info(() -> "Implementation;get();put()");
    initialiseObjects();
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
        {new FastMap<String, Object>(), ""},
        {new FastHashMap(), ""},
        {new FastHashMap(SIZE), "SIZE"},
        {new TreeMap<String, Object>(), ""},
        {Maps.newHashMap(), ""},
        {new FastTreeMap(), ""},
    };
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis"})
  @Test(dataProvider = "mapProvider", singleThreaded = true)
  public void test(Map<String, Object> map, String typeExtension) {
    String type = map.getClass().getName() + "(" + typeExtension + ")";

    stopWatch.start(type + "put()");
    for (String o: objects)
      map.put(o, o);
    stopWatch.stop();

    stopWatch.start(type + "get()");
    for (String o: objects)
      map.get(o);
    long putTime = stopWatch.getLastTaskTimeMillis();
    stopWatch.stop();
    long getTime = stopWatch.getLastTaskTimeMillis();
    LOGGER.info(() -> type + ";" + getTime + ";" + putTime);
    map.clear();
  }

  @AfterClass
  public void tearDown() {
    LOGGER.info(() -> stopWatch.prettyPrint());
  }

  private void initialiseObjects() {
    objects = new HashSet<>();
    for (int i = 0; i < SIZE; i++)
      objects.add(Integer.toString(i));
  }
}
