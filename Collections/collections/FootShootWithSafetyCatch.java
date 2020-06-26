package collections;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum FootShootWithSafetyCatch {
  ;
  private static List<String> names =
      Collections.checkedList(new ArrayList<String>(), String.class);

  static {
    Collections.addAll(names, "John", "Anton", "Heinz");
  }

  @SuppressWarnings(
      {"rawtypes", "unchecked", "PMD.DoNotCallSystemExit", "PMD.LawOfDemeter"})
  public static void
  main(String... args) {
    Thread.currentThread().setUncaughtExceptionHandler((t, e) -> {
      System.err.printf("Thread %s throws following exception: %s%n", t, e);
      System.out.println("Printing names...");
      System.out.println(names.stream().collect(joining("+")));
      names.clear();
      System.exit(1);
    });

    List huh = names;
    List<Integer> numbers = huh;
    numbers.add(42);
    System.out.println(names.stream().collect(joining("+")));
    names.clear();
  }
}
