package javapuzzles;

public enum Compliance {
  ;

  // Java 11 compliance
  public static void main(String... args) {
    var b1 = " ".isBlank();
    var b2 = " ".isEmpty();
    System.out.println(b1 + " " + b2);
  }

}
