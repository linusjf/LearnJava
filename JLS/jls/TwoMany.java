package jls;

@SuppressWarnings("PMD")
public final class TwoMany {
  private TwoMany() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  static void howMany(int k) {
    switch (k) {
      case 1:
        System.out.println("one");
        // exit the switch
        break;
      case 2:
        System.out.println("two");
        // exit the switch
        break;
      case 3:
        System.out.println("many");
        // not needed, but good style
        break;
    }
  }

  static void howManyAgain(int k) {
    switch (k) {
      case 1 -> System.out.println("one");
      case 2 -> System.out.println("two");
      case 3 -> System.out.println("many");
    }
  }

  public static void main(String... args) {
    howMany(1);
    howMany(2);
    howMany(3);
    howManyAgain(1);
    howManyAgain(2);
    howManyAgain(3);
  }
}
