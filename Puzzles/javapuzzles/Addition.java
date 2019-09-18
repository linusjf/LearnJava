package javapuzzles;

public enum Addition {
  ;
  public static void main(String... args) {
    int a = 45;
    int d;
    double b = 3.1415987;
    long c = 70000000;
    a += b;
    System.out.println(a);
    a += c;
    System.out.println(a);
    // Errors printed out
    // d  = d + b;
    // d = d + c;
  }
}
