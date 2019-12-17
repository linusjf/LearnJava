package threads;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public enum CancelledExecutor {
  ;

  public static void main(String[] args) {
    ThreadPoolExecutor executor =
        (ThreadPoolExecutor)Executors.newCachedThreadPool();
    Task task = new Task();
    System.out.printf("Main: Executing the Task%n");
    Future<?> result = executor.submit(task);
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    System.out.printf("Main: Canceling the Task%n");
    result.cancel(true);
    System.out.printf("Main: Canceled: %s%n",
                      result.isCancelled());
    System.out.printf("Main: Done: %s%n", result.isDone());
    executor.shutdown();
    System.out.printf("Main: The executor has finished%n");
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Task implements Runnable {
    @Override
    public void run() {
      while (true) {
        try {
          System.out.printf("Task: Test%n");

          Thread.sleep(100);
        } catch (InterruptedException e) {
          System.err.println(e);
          return;
        }
      }
    }
  }
}
