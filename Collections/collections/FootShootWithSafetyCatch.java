package collections;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import static java.util.stream.Collectors.joining;

public enum FootShootWithSafetyCatch {
  ;
  private static List<String> names;

  public static void main(String... args) {
    Thread.currentThread().setUncaughtExceptionHandler((t,e) -> {
      System.err.printf("Thread %s throws following exception: %s%n",t,e);
    System.out.println("Printing names...");
    System.out.println(names.stream().collect(joining("+")));
    }
    );

    names = Collections.checkedList(
        new ArrayList<String>(), String.class);
    Collections.addAll(names, "John", "Anton", "Heinz");
    List huh = names;
    List<Integer> numbers = huh;
    numbers.add(42);
    System.out.println(names.stream().collect(joining("+")));
  }
}
