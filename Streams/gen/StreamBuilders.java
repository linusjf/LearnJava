package gen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum StreamBuilders {
  ;

  public static void main(String[] args) {
    streamOf();
    arrayOf();
    listStream();
    streamGenerate();
    tokenStreams();
  }

  public static void streamOf() {
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    stream.forEach(p -> System.out.println(p));
  }

  public static void arrayOf() {
    Stream<Integer> stream =
        Stream.of(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
    stream.forEach(p -> System.out.println(p));
  }

  public static void listStream() {
    List<Integer> list = new ArrayList<>();

    for (int i = 1; i < 10; i++) {
      list.add(i);
    }

    Stream<Integer> stream = list.stream();
    stream.forEach(p -> System.out.println(p));
  }

  public static void streamGenerate() {
    // clang-format off
    Stream<Date> stream = Stream.generate(Date::new);
    // clang-format on
    stream.limit(50).forEach(System.out::println);
  }

  public static void tokenStreams() {
    IntStream stream = "12345_abcdefg".chars();
    stream.forEach(p -> System.out.println(p));

    // OR

    Stream<String> stream1 = Stream.of("A$B$C".split("\\$"));
    stream1.forEach(p -> System.out.println(p));
  }
}
