package threads;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public enum DelayedExecutor {
  ;

  private static ScheduledThreadPoolExecutor executor =
        (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1);

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    System.out.printf("Main: Starting at: %s%n", new Date());
    for (int i = 0; i < 5; i++) {
      Task task = new Task("Task " + i);
      executor.schedule(task, (i + 1) * (i + 1), TimeUnit.SECONDS);
    }
    executor.shutdown();
    try {
      executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    System.out.printf("Main: Ends at: %s%n", new Date());
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Task implements Callable<String> {
    private final String name;

    Task(String name) {
      this.name = name;
    }

    @Override
    public String call() throws Exception {
      System.out.printf("%s: Starting at : %s%n", name, new Date());
      return "Hello, world";
    }
  }
}
