package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public enum InvokeAllExecutor {
  ;

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis","PMD.LawOfDemeter"})
  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    List<Task> taskList = new ArrayList<>();
    for (int i = 0; i < 3; i++) 
      taskList.add(new Task("Task " + i));
    List<Future<Result>> resultList = null;
    try {
      resultList = executor.invokeAll(taskList);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    executor.shutdown();
    System.out.println("Main: Printing the results");
    for (Future<Result> future : resultList) {
      try {
        Result result = future.get();
        System.out.println(result.getName() + ": " + result.getValue());
      } catch (InterruptedException | ExecutionException e) {
        System.err.println(e);
      }
    }
  }

  @SuppressWarnings("PMD.DataClass")
  static class Result {
    private String name;
    private int value;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Task implements Callable<Result> {
    private final String name;
    private final Random random = new Random();

    Task(String name) {
      this.name = name;
    }

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public Result call() throws Exception {
      try {
        long duration = random.nextInt(10);

        System.out.printf("%s: Waiting %d seconds for results.%n", this.name, duration);
        TimeUnit.SECONDS.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
      int value = 0;
      for (int i = 0; i < 5; i++) 
        value += random.nextInt(100);
      Result result = new Result();
      result.setName(this.name);
      result.setValue(value);
      System.out.println(this.name + ": Ends");
      return result;
    }
  }
}
