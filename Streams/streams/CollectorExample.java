package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CollectorExample {
  ;
  public static void main(String... args) {
    Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8 };
    List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));

    System.out.println("Parallel Stream: ");
    listOfIntegers.stream().parallel().forEach(e -> System.out.print(e + " "));
    System.out.println();

    // Collectors
    List<Integer> l = listOfIntegers.stream()
      .parallel()
      .collect(Collectors.toList());
    System.out.println(l);
  }
}
