package javapuzzles;

public enum SubNum {
  ;

  public static void main(String... args) {
    System.out.println(computeSum());
    System.out.println(computeSumAgain());
    System.out.println(computeSummer());
    System.out.println(computeSumExpanded());
    System.out.println(computeSumSub());
    System.out.println(computeSumMinus());
    System.out.println(computeSumMinus30());
  }

  private static int computeSumExpanded() {
    int sum = 0;
    int mod = 2;
    int b = mod - 1;
    sum += mod * b / 2;
    return sum;
  }

  private static int computeSumMinus() {
    int sum = 0;
    int mod = 2;
    sum += mod * (mod - 1) / 2;
    return sum;
  }

  private static int computeSumMinus30() {
    int sum = 0;
    int mod = 2;
    sum += mod * (mod -= 30) / 2;
    return sum;
  }

  private static int computeSumSub() {
    int sum = 0;
    int mod = 2;
    sum += mod * (mod = mod - 1) / 2;
    return sum;
  }

  private static int computeSum() {
    int sum = 0;
    int mod = 2;
    sum += mod * --mod / 2;
    return sum;
  }

  private static int computeSumAgain() {
    int sum = 0;
    int mod = 2;
    sum += mod * (--mod / 2);
    return sum;
  }

  private static int computeSummer() {
    int sum = 0;
    int mod = 2;
    sum += (mod * --mod) / 2;
    return sum;
  }
}
