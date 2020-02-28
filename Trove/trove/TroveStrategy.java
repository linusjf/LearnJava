package trove;

import gnu.trove.map.hash.TCustomHashMap;
import java.util.Map;
import java.util.logging.Logger;

public enum TroveStrategy {
  ;

  private static final Logger LOGGER =
      Logger.getLogger(TroveStrategy.class.getName());

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String... args) {
    char[] foo = new char[] {'a', 'b', 'c'};
    char[] bar = new char[] {'a', 'b', 'c'};
    LOGGER.info(() -> foo.hashCode() == bar.hashCode() ? "equal" : "not equal");
    LOGGER.info(() -> foo.equals(bar) ? "equal" : "not equal");
    Map<Object, Object> map = new TCustomHashMap<>(new CharArrayStrategy());
    map.put(foo, "foo");
    LOGGER.info(() -> map.get(bar).toString());
  }
}
