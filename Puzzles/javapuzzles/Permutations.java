package javapuzzles;

// https://gist.github.com/jaycobbcruz/cbabc1eb49f51bfe2ed1db10a06a2b26
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum Permutations {
  ;

  @SuppressWarnings("PMD.ShortMethodName")
  public static <T> Stream<Stream<T>> of(
      final List<T> items) {
    return IntStream.range(0, factorial(items.size()))
        .mapToObj(i -> permutation(i, items).stream());
  }

  private static int factorial(final int num) {
    return IntStream.rangeClosed(2, num).reduce(
        1, (x, y) -> x * y);
  }

  private static <T> List<T> permutation(
      final int count,
      final List<T> input,
      final List<T> output) {
    if (input.isEmpty()) {
      return output;
    }

    final int factorial = factorial(input.size() - 1);
    System.out.printf("Factorial = %d%n", factorial);
    System.out.println("input = " + input);
    System.out.println("count = " + count);
    System.out.printf("count/factorial = %d%n",
                      count / factorial);
    output.add(input.remove(count / factorial));
    return permutation(count % factorial, input, output);
  }

  private static <T> List<T> permutation(
      final int count,
      final List<T> items) {
    return permutation(
        count, new LinkedList<>(items), new ArrayList<>());
  }

  public static void main(String... args) {
    // clang-format off
    Permutations.of(Arrays.asList(1, 2, 3))
        .forEach(
            p -> {
              p.forEach(System.out::print);
              System.out.print(" ");
            });
    // clang-format on
  }
}
