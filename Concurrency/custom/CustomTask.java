package custom;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomTask implements Runnable, Comparable<CustomTask> {
  private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
  private final int priority;
  private final transient String name;

  public CustomTask(String name, int priority) {
    this.name = name;
    this.priority = priority;
  }

  public int getPriority() {
    return priority;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public int hashCode() {
    return Integer.valueOf(priority).hashCode();
  }

  @Override
  public int compareTo(CustomTask o) {
    if (this.getPriority() < o.getPriority()) return 1;
    if (this.getPriority() > o.getPriority()) return -1;
    return 0;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CustomTask)) return false;
    return compareTo((CustomTask) o) == 0;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void run() {
    System.out.printf("CustomTask: %s Priority : %d%n", name, priority);
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    for (int i = 0; i < 4; i++) {
      CustomTask task = new CustomTask("Task " + i, i);
      executor.execute(task);
    }
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    for (int i = 4; i < 8; i++) {
      CustomTask task = new CustomTask("Task " + i, i);
      executor.execute(task);
    }
    executor.shutdown();
    try {
      executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    System.out.printf("Main: End of the program.%n");
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "CustomTask(priority=" + this.getPriority() + ", name=" + this.name + ")";
  }
}
