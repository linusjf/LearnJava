package threads;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("PMD.SystemPrintln")
public enum CancelForkJoinPool {
  ;

  public static void main(String[] args) {
    ArrayGenerator generator = new ArrayGenerator();
    int[] array = generator.generateArray(1000);
    TaskManager manager = new TaskManager();
    ForkJoinPool pool = new ForkJoinPool();
    SearchNumberTask task = new SearchNumberTask(array, 0, 1000, 5, manager);
    pool.execute(task);
    pool.shutdown();
    try {
      pool.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    System.out.printf("Main: The program has finished%n");
  }

  static class ArrayGenerator {
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public int[] generateArray(int size) {
      int[] array = new int[size];
      Random random = new Random();
      for (int i = 0; i < size; i++)
        array[i] = new Random(random.nextLong()).nextInt(10);
      return array;
    }
  }

  static class TaskManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<ForkJoinTask<Integer>> tasks;

    TaskManager() {
      tasks = new ArrayList<>();
    }

    public void addTask(ForkJoinTask<Integer> task) {
      tasks.add(task);
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    public void cancelTasks(ForkJoinTask<Integer> cancelTask) {
      for (ForkJoinTask<Integer> task: tasks) {
        if (!task.equals(cancelTask)) {
          task.cancel(true);
          ((SearchNumberTask)task).writeCancelMessage();
        }
      }
    }
  }

  static class SearchNumberTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    private static final int TASK_SIZE_THRESHOLD = 10;
    private static final int NOT_FOUND = -1;

    private final int[] numbers;
    private final int start;
    private final int end;
    private final int number;
    private final TaskManager manager;

    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    SearchNumberTask(int[] numbers,
                     int start,
                     int end,
                     int number,
                     TaskManager manager) {
      this.numbers = numbers;
      this.start = start;
      this.end = end;
      this.number = number;
      this.manager = manager;
    }

    @Override
    protected Integer compute() {
      System.out.println("Task: " + start + ":" + end);
      return end - start > TASK_SIZE_THRESHOLD ? launchTasks()
                                               : lookForNumber();
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    private int lookForNumber() {
      for (int i = start; i < end; i++) {
        if (numbers[i] == number) {
          System.out.printf(
              "Task: Number %d found in position %d%n", number, i);
          manager.cancelTasks(this);
          return i;
        }
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      }
      return NOT_FOUND;
    }

    private int launchTasks() {
      int mid = (start + end) / 2;

      SearchNumberTask task1 =
          new SearchNumberTask(numbers, start, mid, number, manager);
      SearchNumberTask task2 =
          new SearchNumberTask(numbers, mid, end, number, manager);
      manager.addTask(task1);
      manager.addTask(task2);
      task1.fork();
      task2.fork();
      int returnValue;

      returnValue = task1.join();
      if (returnValue != -1)
        return returnValue;

      return task2.join();
    }

    public void writeCancelMessage() {
      System.out.printf("Task: Canceled task from %d to %d%n", start, end);
    }
  }
}
