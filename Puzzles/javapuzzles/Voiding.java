package javapuzzles;

public enum Voiding {
;

  public static void main(String... args) {
    Void[] voids = new Void[1];
    System.out.println(voids);
    voids = new Void[0];
    System.out.println(voids);
    voids = new Void[-20];
    System.out.println(voids);
  }
}
