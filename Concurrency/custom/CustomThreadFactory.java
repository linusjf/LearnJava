package custom;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class CustomThreadFactory implements ThreadFactory {
  private static ExecutorService executor;
  private int counter;
  private final String prefix;

  public CustomThreadFactory(String prefix) {
    this.prefix = prefix;
    counter = 1;
  }

  public static void main(String[] args) {
    try {
      CustomThreadFactory myFactory =
          new CustomThreadFactory("CustomThreadFactory");
      CustomTask task = new CustomTask();
      Thread thread = myFactory.newThread(task);
      thread.start();
      thread.join(1000);
      System.out.printf("Main: End of the example.%n");
      alternateMain();
    } catch (InterruptedException | ExecutionException ie) {
      System.err.println(ie);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void alternateMain()
      throws InterruptedException, ExecutionException {
    CustomThreadFactory threadFactory =
        new CustomThreadFactory("CustomThreadFactory-alternate");
    executor = Executors.newCachedThreadPool(threadFactory);
    CustomTask task = new CustomTask();
    Future<?> result = executor.submit(task);
    executor.shutdown();
    if (executor.awaitTermination(1, TimeUnit.DAYS))
      System.out.printf("Alternate Main: End of the program.%n");
    if (result.get() == null)
      System.out.printf("Task completed successfully%n");
  }

  @Override
  public Thread newThread(Runnable r) {
    CustomThread myThread = new CustomThread(r, prefix + "-" + counter);
    counter++;
    return myThread;
  }

  static class CustomThread extends Thread {
    private final Date creationDate;
    private long executionTime;

    CustomThread(Runnable target, String name) {
      super(target, name);
      creationDate = new Date();
    }

    @Override
    public void run() {
      Date startDate = new Date();
      super.run();
      Date finishDate = new Date();
      executionTime = finishDate.getTime() - startDate.getTime();
      System.out.printf("%s: Thread information.%n", getName());
      System.out.printf("%s%n", this);
    }

    public long getExecutionTime() {
      return executionTime;
    }

    @Override
    public String toString() {
      StringBuilder buffer = new StringBuilder(50);
      buffer.append(getName())
          .append(":  Creation Date: ")
          .append(creationDate)
          .append(" : Running time: ")
          .append(getExecutionTime())
          .append(" Milliseconds.");
      return buffer.toString();
    }
  }

  static class CustomTask implements Runnable {
    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void run() {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof CustomThreadFactory))
      return false;
    CustomThreadFactory other = (CustomThreadFactory)o;
    if (!other.canEqual((Object)this))
      return false;
    if (this.counter != other.counter)
      return false;
    Object this$prefix = this.prefix;
    Object other$prefix = other.prefix;
    if (this$prefix == null ? other$prefix != null
                            : !this$prefix.equals(other$prefix))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof CustomThreadFactory;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.counter;
    Object $prefix = this.prefix;
    result = result * PRIME + ($prefix == null ? 43 : $prefix.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "CustomThreadFactory(counter=" + this.counter
        + ", prefix=" + this.prefix + ")";
  }
}
