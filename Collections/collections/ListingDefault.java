package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://www.baeldung.com/java-init-list-one-line
public enum ListingDefault {
  ;
  private static final String FOO = "foo";
  private static final String BAZ = "baz";
  private static final String BAR = "bar";

  private static int loopCount;

  static {
    Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
      System.err.println(e);
      System.err.printf(" in thread %s...", t);
      loopCount++;
      main();
    });
  }

  @SuppressWarnings("PMD.DoubleBraceInitialization")
  public static void main(String... args) {
    List<String> list = Arrays.asList(new String[] {FOO, BAR});
    assert list.contains(FOO);

    list = Arrays.asList(FOO, BAR);

    assert list.contains(FOO);

    if (loopCount == 0)
      list.add(BAZ);

    String[] array = {FOO, BAR};
    list = Arrays.asList(array);
    array[0] = BAZ;
    assert BAZ == list.get(0);
    assert array[0] == list.get(0);

    list = Stream.of(FOO, BAR).collect(Collectors.toList());

    assert list.contains(FOO);
    list = List.of(FOO, BAR, BAZ);
    List<String> cities = new ArrayList<>() {
      {
        add("New York");
        add("Rio");
        add("Tokyo");
      }
    };

    System.out.println(cities.contains("Rio"));
    assert cities.contains("Rio");
  }
}
