package threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public enum ControllingExecutor {
  ;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    ResultTask[] resultTasks = new ResultTask[5];
    for (int i = 0; i < 5; i++) {
      resultTasks[i] = new ResultTask(new ExecutableTask("Task " + i));
      executor.submit(resultTasks[i]);
    }
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e1) {
      System.err.println(e1);
    }
    for (ResultTask task : resultTasks) task.cancel(true);
    for (ResultTask task : resultTasks) {
      try {
        if (!task.isCancelled()) System.out.printf("%s%n", task.get());
      } catch (InterruptedException | ExecutionException e) {
        System.err.println(e);
      }
      executor.shutdown();
    }
  }

  static class ExecutableTask implements Callable<String> {
    private final String name;

    ExecutableTask(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String call() throws Exception {
      try {
        long duration = (long) (Math.random() * 10);
        System.out.printf("%s: Waiting %d seconds for results.%n", this.name, duration);
        TimeUnit.SECONDS.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
      return "Hello, world. I'm " + name;
    }
  }

  static class ResultTask extends FutureTask<String> {
    private final String name;

    ResultTask(Callable<String> callable) {
      super(callable);
      this.name = ((ExecutableTask) callable).getName();
    }

    @Override
    protected void done() {
      if (isCancelled()) {
        System.out.printf("%s: Has been canceled%n", name);
      } else {
        System.out.printf("%s: Has finished%n", name);
      }
    }
  }
}
