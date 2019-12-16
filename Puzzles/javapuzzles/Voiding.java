package javapuzzles;

import java.util.Arrays;

public enum Voiding {
  ;

  @SuppressWarnings("checkstyle:magicnumber")
  public static void main(String... args) {
    try {
      Void[] voids = new Void[1];
      System.out.println(Arrays.toString(voids));
      voids = new Void[0];
      System.out.println(Arrays.toString(voids));
      voids = new Void[-20];
      System.out.println(Arrays.toString(voids));
    } catch (NegativeArraySizeException nase) {
      System.err.println(nase.getMessage());
    }
  }
}
