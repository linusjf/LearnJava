package javapuzzles;

import java.util.Random;

@SuppressWarnings("PMD")
public enum Rhymes {
  ;
  private static Random rnd = new Random();

  @SuppressWarnings("fallthrough")
  public static void main(String[] args) {
    StringBuilder word = null;
    switch (rnd.nextInt(2)) {
      case 1:
        word = new StringBuilder('P');
      case 2:
        word = new StringBuilder('G');
      default:
        word = new StringBuilder('M');
    }
    word.append("ain");
    System.out.println(word);
    fixedMain(args);
    elegantMain(args);
    generalMain(args);
  }

  public static void fixedMain(String... args) {
    StringBuilder word = null;
    switch (rnd.nextInt(3)) {
      case 1:
        word = new StringBuilder("P");
        break;
      case 2:
        word = new StringBuilder("G");
        break;
      default:
        word = new StringBuilder("M");
        break;
    }
    word.append("ain");
    System.out.println(word);
  }

  public static void elegantMain(String... args) {
    System.out.println("PGM".charAt(rnd.nextInt(3))
                       + "ain");
  }

  public static void generalMain(String... args) {
    String[] a = {"Main", "Pain", "Gain"};
    System.out.println(randomElement(a));
  }

  private static String randomElement(String... a) {
    return a[rnd.nextInt(a.length)];
  }
}
