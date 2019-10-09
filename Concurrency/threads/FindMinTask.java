package threads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FindMinTask implements Callable<Integer> {
  private static final int MIN_SIZE = 2;
  private int[] numbers;
  private int startIndex;
  private int endIndex;
  private ExecutorService executorService;

  @SuppressWarnings("PMD.ArrayIsStoredDirectly")
  public FindMinTask(
    ExecutorService executorService,
    int[] numbers,
    int startIndex,
    int endIndex
  ) {
    this.executorService = executorService;
    this.numbers = numbers;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  public Integer call() throws InterruptedException, ExecutionException {
    int sliceLength = (endIndex - startIndex) + 1;
    if (sliceLength > MIN_SIZE) {
      FindMinTask lowerFindMin = new FindMinTask(
        executorService,
        numbers,
        startIndex,
        startIndex + (sliceLength / 2) - 1
      );
      Future<Integer> futureLowerFindMin = executorService.submit(lowerFindMin);
      FindMinTask upperFindMin = new FindMinTask(
        executorService,
        numbers,
        startIndex + (sliceLength / 2),
        endIndex
      );
      Future<Integer> futureUpperFindMin = executorService.submit(upperFindMin);
      return Math.min(futureLowerFindMin.get(), futureUpperFindMin.get());
    } else {
      return Math.min(numbers[startIndex], numbers[endIndex]);
    }
  }

  public static void main(String[] args) {
    int[] numbers = new int[10_000];
    Random random = new Random(System.currentTimeMillis());
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = Math.abs(random.nextInt());
    }
    try {
      ExecutorService executorService = Executors.newFixedThreadPool(6400);
      Future<Integer> futureResult = executorService.submit(
        new FindMinTask(executorService, numbers, 0, numbers.length - 1)
      );
      System.out.println(futureResult.get());
      executorService.shutdown();
    } catch (ExecutionException | InterruptedException e) {
      System.err.println(e);
    }
  }
}
