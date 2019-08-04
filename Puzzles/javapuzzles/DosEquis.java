package javapuzzles;

import java.util.IllegalFormatConversionException;

@SuppressWarnings("PMD")
public enum DosEquis {
  ;

  public static void main(String[] args) {
    char x = 'X';
    int i = 0;
    System.out.println(true ? x : 0);
    System.out.println(false ? i : x);

    System.out.printf("%c%n", true ? x : 0);
    System.out.printf("%c%n", false ? i : x);

    try {
      System.out.printf("%d%n", true ? x : 0);
    } catch (IllegalFormatConversionException ifce) {
      System.err.println(ifce);
    }
    System.out.printf("%d%n", false ? i : x);

    System.out.printf("%s%n", true ? x : 0);
    System.out.printf("%s%n", false ? i : x);
  }
}
