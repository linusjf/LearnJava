package optional;

import static java.util.Optional.*;

import java.util.Locale;
import java.util.Optional;

public enum OptionalDemo {
  ;
  static final String[] WORDS = new String[10];

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    Optional<String> checkNull = ofNullable(WORDS[5]);

    testOptional(checkNull);

    WORDS[5] = "Hello";
    checkNull = of(WORDS[5]);

    testOptional(checkNull);

    Integer value1 = null;
    Integer value2 = 10;
    Optional<Integer> a = ofNullable(value1);

    // Optional.of - throws NullPointerException if passed parameter is null
    Optional<Integer> b = of(value2);
    System.out.println(sum(a, b));
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void testOptional(
      Optional<String> checkNull) {
    if (checkNull.isPresent()) {
      String word =
          checkNull.get().toLowerCase(Locale.getDefault());
      System.out.println(word);
    } else
      System.out.println("word is null");
  }

  public static Integer sum(Optional<Integer> a,
                            Optional<Integer> b) {
    // Optional.isPresent - checks the value is present or not
    System.out.println("First parameter is present: "
                       + a.isPresent());
    System.out.println("Second parameter is present: "
                       + b.isPresent());

    // Optional.orElse - returns the value if present otherwise returns
    // the default value passed.
    Integer value1 = a.orElse(0);

    // Optional.get - gets the value, value should be present
    Integer value2 = b.get();
    return value1 + value2;
  }
}
