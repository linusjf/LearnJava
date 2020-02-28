package trove;

import java.util.logging.Logger;

public enum TroveStrategy {
  ;

  private static final Logger LOGGER =
      Logger.getLogger(TroveStrategy.class.getName());

  public static void main(String... args) {
    char[] foo = new char[] {'a', 'b', 'c'};
    char[] bar = new char[] {'a', 'b', 'c'};
    LOGGER.info(() -> foo.hashCode() == bar.hashCode() ? "equal" : "not equal");
    LOGGER.info(() -> foo.equals(bar) ? "equal" : "not equal");
  }
}
