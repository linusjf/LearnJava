package threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class MultithreadedMaxFinder {
  private static int[] data =
      // clang-format off
      new int[] {
        12, 23, 45, 46, 56, 76, 87, 98, 78, 123,
        45, 56, 76, 98, 90, 900, 567, 456, 24, 45,
        43, 45, 23, 1000, 456, 678, 1002, 12_345, 567, 567
      };
  // clang-format on

  private MultithreadedMaxFinder() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings({"checkstyle:hiddenfield", "PMD.LawOfDemeter"})
  public static int max(int... data)
      throws InterruptedException, ExecutionException {
    if (data.length == 1)
      return data[0];
    else if (data.length == 0)
      throw new IllegalArgumentException();

    // split the job into 2 pieces
    FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
    FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);

    // spawn 2 threads
    ExecutorService service = Executors.newFixedThreadPool(2);
    Future<Integer> future1 = service.submit(task1);
    Future<Integer> future2 = service.submit(task2);
    int val = future1.get();
    int val2 = future2.get();
    service.shutdown();
    return Math.max(val, val2);
  }

  public static void main(String[] args) {
    try {
      System.out.println("Maximum = " + max(data));
    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e.getMessage());
    }
  }
}
