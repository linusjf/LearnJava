package collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

// https://www.baeldung.com/java-init-list-one-line
public enum Listing {

  ;

  private static int loopCount = 0;

  public static void main(String... args) {

    Thread.currentThread().setUncaughtExceptionHandler( (t,e) -> {
      System.err.println(e);
      System.err.printf(" in thread %s...",t);
      loopCount++;
      main();
    });
    
    List<String> list = Arrays.asList(new String[] {"foo", "bar"});
    assert list.contains("foo");

    list = Arrays.asList("foo", "bar");

    assert list.contains("foo");

    if (loopCount == 0)
      list.add("baz");

    String[] array = {"foo", "bar"};
    list = Arrays.asList(array);
    array[0] = "baz";
    assert "baz" == list.get(0);

    list = Stream.of("foo", "bar").collect(Collectors.toList());

    assert list.contains("foo");
    list = List.of("foo", "bar", "baz");
    Set<String> set = Set.of("foo", "bar", "baz");
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
