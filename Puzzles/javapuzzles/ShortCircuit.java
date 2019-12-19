package javapuzzles;

public enum ShortCircuit {
  ;

  @SuppressWarnings({"PMD.EqualsNull","PMD.LawOfDemeter"})
  public static void main(String... args) {
    String x = "ABC";
    String y = null;
    boolean b = "ABC".toString() == x;
    System.out.println(b || y.equals(null));
  }
}
