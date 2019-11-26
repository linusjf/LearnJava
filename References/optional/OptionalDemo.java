package optional;

import java.util.Locale;
import java.util.Optional;

public enum OptionalDemo {
  ;

  public static void main(String[] args) {
    String[] words = new String[10];

    Optional<String> checkNull = Optional.ofNullable(words[5]);

    if (checkNull.isPresent()) {
      String word = words[5].toLowerCase(Locale.getDefault());
      System.out.println(word);
    } else
      System.out.println("word is null");
    words[5] = "Hello";
    checkNull = Optional.of(words[5]);

    if (checkNull.isPresent()) {
      String word = words[5].toLowerCase(Locale.getDefault());
      System.out.println(word);
    } else
      System.out.println("word is null");
    Integer value1 = null;
    Integer value2 = 10;
    Optional<Integer> a = Optional.ofNullable(value1);

    // Optional.of - throws NullPointerException if passed parameter is null
    Optional<Integer> b = Optional.of(value2);
    System.out.println(sum(a, b));
  }

  public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
    // Optional.isPresent - checks the value is present or not
    System.out.println("First parameter is present: " + a.isPresent());
    System.out.println("Second parameter is present: " + b.isPresent());

    // Optional.orElse - returns the value if present otherwise returns
    // the default value passed.
    Integer value1 = a.orElse(0);

    // Optional.get - gets the value, value should be present
    Integer value2 = b.get();
    return value1 + value2;
  }
}
