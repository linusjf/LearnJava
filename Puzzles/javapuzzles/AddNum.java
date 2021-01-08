package javapuzzles;

public enum AddNum {
  ;

  public static void main(String... args) {
    System.out.println(computeSum());
    System.out.println(computeSumAgain());
    System.out.println(computeSummer());
  }

  private static int computeSum() {
    int sum = 0;
    int mod = 1;
    sum += mod * ++mod / 2;
    return sum;
  }

  private static int computeSumAgain() {
    int sum = 0;
    int mod = 1;
    sum += mod * (++mod / 2);
    return sum;
  }

  private static int computeSummer() {
    int sum = 0;
    int mod = 1;
    sum += (mod * ++mod) / 2;
    return sum;
  }
}
