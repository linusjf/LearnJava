package javapuzzles;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://twitter.com/PeterLawrey/status/1348561466060906496?s=19
@SuppressWarnings("all")
public final class StringLock {
  static String hi = "The quick brown fox jumped over the lazy dog.";
  /**
   * static String name = IntStream.range(0, hi.length()) .parallel() .map(i -> hi.charAt(i))
   * .filter(s -> s != ' ') .mapToObj(c -> Character.toString((char)c)) .sorted()
   * .collect(Collectors.joining());
   */
  static String name = IntStream.range(0, hi.length())
                           .map(i -> hi.charAt(i))
                           .filter(s -> s != ' ')
                           .mapToObj(c -> Character.toString((char)c))
                           .sorted()
                           .collect(Collectors.joining());

  private StringLock() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String... args) {
    System.out.println(name);
  }
}
