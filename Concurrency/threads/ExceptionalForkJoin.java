package threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public enum ExceptionalForkJoin {
  ;

  public static void main(String[] args) {
    int[] array = new int[100];
    Task task = new Task(array, 0, 100);
    ForkJoinPool pool = new ForkJoinPool();
    pool.execute(task);
    pool.shutdown();
    try {
      pool.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    if (task.isCompletedAbnormally()) {
      System.out.printf("Main: An exception has ocurred%n");
      System.out.printf("Main: %s%n", task.getException());
    }
    System.out.printf("Main: Result: %d", task.join());
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Task extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    private static final int MIN_TASK_SIZE = 10;
    private final int[] array;
    private final int start;
    private final int end;

    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    Task(int[] array, int start, int end) {
      super();
      this.array = array;
      this.start = start;
      this.end = end;
    }

    @Override
    protected Integer compute() {
      System.out.printf(
          "Task: Start from %d to %d%n", start, end);
      if (end - start < MIN_TASK_SIZE) {
        if (3 > start && 3 < end)
          completeExceptionally(new RuntimeException(
              "This task throws a Runtime "
              + "Exception: Task from " + start + " to "
              + end));
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      } else {
        int mid = (end + start) / 2;
        Task task1 = new Task(array, start, mid);
        Task task2 = new Task(array, mid, end);
        invokeAll(task1, task2);
      }
      System.out.printf(
          "Task: End from %d to %d%n", start, end);
      return 0;
    }
  }
}
