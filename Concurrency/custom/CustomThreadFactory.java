package custom;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class CustomThreadFactory implements ThreadFactory {

  private int counter;
  private String prefix;

  public static void main(String[] args) {
    try {
      CustomThreadFactory myFactory = new CustomThreadFactory("CustomThreadFactory");
      CustomTask task = new CustomTask();
      Thread thread = myFactory.newThread(task);
      thread.start();
      thread.join();
      System.out.printf("Main: End of the example.\n");
      alternateMain();
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }

  public static void alternateMain() throws InterruptedException {
    CustomThreadFactory threadFactory = new CustomThreadFactory("CustomThreadFactory-alternate");
    ExecutorService executor = Executors.newCachedThreadPool(threadFactory);
    CustomTask task = new CustomTask();
    executor.submit(task);
    executor.shutdown();
    if (executor.awaitTermination(1, TimeUnit.DAYS))
      System.out.printf("Alternate Main: End of the program.\n");
  }

  public CustomThreadFactory(String prefix) {
    this.prefix = prefix;
    counter = 1;
  }

  @Override
  public Thread newThread(Runnable r) {
    CustomThread myThread = new CustomThread(r, prefix + "-" + counter);
    counter++;
    return myThread;
  }

  static class CustomThread extends Thread {
    private Date creationDate;
    private Date startDate;
    private Date finishDate;

    CustomThread(Runnable target, String name) {
      super(target, name);
      creationDate = new Date();
    }

    @Override
    public void run() {
      setStartDate();
      super.run();
      setFinishDate();
      System.out.printf("%s: Thread information.\n", getName());
      System.out.printf("%s\n", this);
    }

    public void setStartDate() {
      startDate = new Date();
    }

    public void setFinishDate() {
      finishDate = new Date();
    }

    public long getExecutionTime() {
      return finishDate.getTime() - startDate.getTime();
    }

    @Override
    public String toString() {
      StringBuilder buffer = new StringBuilder(50);
      buffer
          .append(getName())
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
    public void run() {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
  }
}
