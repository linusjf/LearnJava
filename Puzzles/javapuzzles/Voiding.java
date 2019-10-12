package javapuzzles;

public enum Voiding {
  ;

  public static void main(String... args) {
    try {
      Void[] voids = new Void[1];
      System.out.println(voids);
      voids = new Void[0];
      System.out.println(voids);
      voids = new Void[-20];
      System.out.println(voids);
    } catch (NegativeArraySizeException nase) {
      System.err.println(nase);
    }
  }
}
