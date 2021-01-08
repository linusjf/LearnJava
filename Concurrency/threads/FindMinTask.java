package threads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings("PMD.SystemPrintln")
public class FindMinTask implements Callable<Integer> {
  private static final int MIN_SIZE = 2;
  private final int[] numbers;
  private final int startIndex;
  private final int endIndex;
  private final ExecutorService executorService;

  @SuppressWarnings("PMD.ArrayIsStoredDirectly")
  public FindMinTask(ExecutorService executorService,
                     int[] numbers,
                     int startIndex,
                     int endIndex) {
    this.executorService = executorService;
    this.numbers = numbers;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public Integer call() throws InterruptedException, ExecutionException {
    int sliceLength = (endIndex - startIndex) + 1;
    if (sliceLength > MIN_SIZE) {
      FindMinTask lowerFindMin =
          new FindMinTask(executorService,
                          numbers,
                          startIndex,
                          startIndex + (sliceLength / 2) - 1);
      Future<Integer> futureLowerFindMin = executorService.submit(lowerFindMin);
      FindMinTask upperFindMin = new FindMinTask(
          executorService, numbers, startIndex + (sliceLength / 2), endIndex);
      Future<Integer> futureUpperFindMin = executorService.submit(upperFindMin);
      return Math.min(futureLowerFindMin.get(), futureUpperFindMin.get());
    } else
      return Math.min(numbers[startIndex], numbers[endIndex]);
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    int[] numbers = new int[10000];
    Random random = new Random();
    for (int i = 0; i < numbers.length; i++)
      numbers[i] = Math.abs(random.nextInt() % Integer.MAX_VALUE);
    try {
      ExecutorService executorService = Executors.newFixedThreadPool(6400);
      Future<Integer> futureResult = executorService.submit(
          new FindMinTask(executorService, numbers, 0, numbers.length - 1));
      System.out.println(futureResult.get());
      executorService.shutdown();
    } catch (ExecutionException | InterruptedException e) {
      System.err.println(e);
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof FindMinTask))
      return false;
    FindMinTask other = (FindMinTask)o;
    if (!other.canEqual((Object)this))
      return false;
    if (!java.util.Arrays.equals(this.numbers, other.numbers))
      return false;
    if (this.startIndex != other.startIndex)
      return false;
    if (this.endIndex != other.endIndex)
      return false;
    Object this$executorService = this.executorService;
    Object other$executorService = other.executorService;
    if (this$executorService == null
            ? other$executorService != null
            : !this$executorService.equals(other$executorService))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof FindMinTask;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + java.util.Arrays.hashCode(this.numbers);
    result = result * PRIME + this.startIndex;
    result = result * PRIME + this.endIndex;
    Object $executorService = this.executorService;
    result = result * PRIME
             + ($executorService == null ? 43 : $executorService.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "FindMinTask(numbers=" + java.util.Arrays.toString(this.numbers)
        + ", startIndex=" + this.startIndex + ", endIndex=" + this.endIndex
        + ", executorService=" + this.executorService + ")";
  }
}
