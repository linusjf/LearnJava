package threads;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindMin extends RecursiveTask<Integer> {
  private static final long serialVersionUID = 1L;
  private static final int MIN_SIZE = 2;
  private final int[] numbers;
  private final int startIndex;
  private final int endIndex;

  @SuppressWarnings("PMD.ArrayIsStoredDirectly")
  public FindMin(int[] numbers, int startIndex, int endIndex) {
    this.numbers = numbers;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  protected Integer compute() {
    int sliceLength = (endIndex - startIndex) + 1;
    if (sliceLength > MIN_SIZE) {
      FindMin lowerFindMin = new FindMin(numbers, startIndex, startIndex + sliceLength / 2 - 1);
      lowerFindMin.fork();
      FindMin upperFindMin = new FindMin(numbers, startIndex + sliceLength / 2, endIndex);
      upperFindMin.fork();
      return Math.min(lowerFindMin.join(), upperFindMin.join());
    } else return Math.min(numbers[startIndex], numbers[endIndex]);
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    int[] numbers = new int[100];
    Random random = new Random();
    for (int i = 0; i < numbers.length; i++)
      numbers[i] = Math.abs(random.nextInt() % Integer.MAX_VALUE);
    ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    Integer min = pool.invoke(new FindMin(numbers, 0, numbers.length - 1));
    System.out.println(min);
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof FindMin)) return false;
    FindMin other = (FindMin) o;
    if (!other.canEqual((Object) this)) return false;
    if (!java.util.Arrays.equals(this.numbers, other.numbers)) return false;
    if (this.startIndex != other.startIndex) return false;
    if (this.endIndex != other.endIndex) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof FindMin;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + java.util.Arrays.hashCode(this.numbers);
    result = result * PRIME + this.startIndex;
    result = result * PRIME + this.endIndex;
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "FindMin(numbers="
        + java.util.Arrays.toString(this.numbers)
        + ", startIndex="
        + this.startIndex
        + ", endIndex="
        + this.endIndex
        + ")";
  }
}
