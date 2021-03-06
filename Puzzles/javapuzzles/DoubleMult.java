package javapuzzles;

public enum DoubleMult {
  ;

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String... args) {
    double e = 2.0e-6;
    double f = 100_000_000.1;
    System.out.println(e * f);
  }
}
