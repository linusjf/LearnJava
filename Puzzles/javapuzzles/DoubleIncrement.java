package javapuzzles;

public enum DoubleIncrement {
  ;

  public static void main(String... args) {
    double d = 0d;
    for (int i = 0; i < 10; i++) {
      d = d + 0.1;
    }
    System.out.println(d);
    d = 0d;
    for (int i = 0; i < 10; i++) {
      d = d + 0.10000000000000000;
    }
    System.out.println(d);
    d = 0d;
    for (int i = 0; i < 10; i++) {
      d = d + Math.pow(10,-1);
    }
    System.out.println(d);
  }
}
