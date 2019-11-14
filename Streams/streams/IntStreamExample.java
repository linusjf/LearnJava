package streams;

import java.util.stream.IntStream;

public enum IntStreamExample {
  ;

  public static void main(String... args) {
    IntStream.range(0, 5).parallel().sorted().forEach(System.out::print);
  }
}
