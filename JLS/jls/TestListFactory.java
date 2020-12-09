package jls;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public final class TestListFactory {

  private TestListFactory() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String... args) {
    ListFactory lf = ArrayList::new;
    ListFactoryAgain lfAgain = ArrayList::new;
    List<String> ls = lf.make();
    List<Number> ln = lf.make();
  }

  static interface ListFactory { <T> List<T> make(); }

  static interface ListFactoryAgain<T> { List<T> make(); }
}
