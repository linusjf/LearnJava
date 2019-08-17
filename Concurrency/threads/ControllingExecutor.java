package threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public enum ControllingExecutor {
  ;

  public static void main(String[] args) {
    ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
    ResultTask[] resultTasks = new ResultTask[5];
    for (int i = 0; i < 5; i++) {
      ExecutableTask executableTask = new ExecutableTask("Task " + i);
      resultTasks[i] = new ResultTask(executableTask);
      executor.submit(resultTasks[i]);
    }
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e1) {
      System.err.println(e1);
    }
    for (ResultTask task : resultTasks) {
      task.cancel(true);
    }
    for (ResultTask task : resultTasks) {
      try {
        if (!task.isCancelled()) {
          System.out.printf("%s\n", task.get());
        }
      } catch (InterruptedException | ExecutionException e) {
        System.err.println(e);
      }
      executor.shutdown();
    }
  }

  static class ExecutableTask implements Callable<String> {
    private String name;

    public String getName() {
      return name;
    }

    ExecutableTask(String name) {
      this.name = name;
    }

    @Override
    public String call() throws Exception {
      try {
        long duration = (long) (Math.random() * 10);
        System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
        TimeUnit.SECONDS.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
      return "Hello, world. I'm " + name;
    }
  }

  static class ResultTask extends FutureTask<String> {
    private String name;

    ResultTask(Callable<String> callable) {
      super(callable);
      this.name = ((ExecutableTask) callable).getName();
    }

    @Override
    protected void done() {
      if (isCancelled()) {
        System.out.printf("%s: Has been canceled\n", name);
      } else {
        System.out.printf("%s: Has finished\n", name);
      }
    }
  }
}
