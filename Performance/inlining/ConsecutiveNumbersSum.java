package inlining;

public class ConsecutiveNumbersSum {

  private final int totalNumbers;

  public ConsecutiveNumbersSum(int totalNumbers) {
    this.totalNumbers = totalNumbers;
  }

  public long getTotalSum() {
    long totalSum = 0;
    for (int i = 0; i <= totalNumbers; i++)
      totalSum += i;
    return totalSum;
  }

  private static long calculateSum(int n) {
    return new ConsecutiveNumbersSum(n).getTotalSum();
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... args) {
    int numIterations = Integer.parseInt(args[0]);
    for (int i = 1; i < numIterations; i++) {
      long sum = calculateSum(i);
      assert sum > 0L;
    }
  }
}
