package threads;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public enum AsynchronousExample {
  ;

  public static void main(String[] args) {
    ForkJoinPool pool = new ForkJoinPool();
    FolderProcessor system =
        new FolderProcessor("/data/data/com.termux/files/home/LearnNodeJS", "log");
    FolderProcessor apps = new FolderProcessor("/data/data/com.termux/files/home/", "log");
    FolderProcessor documents =
        new FolderProcessor("/data/data/com.termux/files/home/LearnJava", "log");
    pool.execute(system);
    pool.execute(apps);

    // CPD-OFF
    pool.execute(documents);
    do {
      System.out.printf("******************************************\n");
      System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
      System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
      System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
      System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
      System.out.printf("******************************************\n");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    } while (!system.isDone() || !apps.isDone() || !documents.isDone());

    // CPD-ON
    pool.shutdown();
    List<String> results;

    results = system.join();
    System.out.printf("System: %d files found.\n", results.size());

    results = apps.join();
    System.out.printf("Apps: %d files found.\n", results.size());

    results = documents.join();
    System.out.printf("Documents: %d files found.\n", results.size());
  }

  static class FolderProcessor extends RecursiveTask<List<String>> {
    private static final long serialVersionUID = 1L;
    private static final int TASK_THRESHOLD = 50;
    private final String path;
    private final String extension;

    FolderProcessor(String path, String extension) {
      super();
      this.path = path;
      this.extension = extension;
    }

    @Override
    protected List<String> compute() {
      List<String> list = new ArrayList<>();
      List<FolderProcessor> tasks = new ArrayList<>();
      File file = new File(path);
      File[] content = file.listFiles();

      if (content != null) {
        for (File f : content) {
          if (f.isDirectory()) {
            FolderProcessor task = new FolderProcessor(f.getAbsolutePath(), extension);
            task.fork();
            tasks.add(task);
          } else {
            if (checkFile(f.getName())) list.add(f.getAbsolutePath());
          }
        }
        if (tasks.size() > TASK_THRESHOLD)
          System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
      }

      addResultsFromTasks(list, tasks);
      return list;
    }

    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
      for (FolderProcessor item : tasks) list.addAll(item.join());
    }

    private boolean checkFile(String name) {
      return name.endsWith(extension);
    }
  }
}
