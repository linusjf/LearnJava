package custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("PMD.SystemPrintln")
public class CustomExecutor extends ThreadPoolExecutor {
  private static final int FIVE = 5;
  private final ConcurrentHashMap<String, Date> startTimes;

  public CustomExecutor(int corePoolSize,
                        int maximumPoolSize,
                        long keepAliveTime,
                        TimeUnit unit,
                        BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    startTimes = new ConcurrentHashMap<>();
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    CustomExecutor myExecutor = new CustomExecutor(
        2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
    final List<Future<String>> results = new ArrayList<>(10);
    for (int i = 0; i < 10; i++)
      results.add(myExecutor.submit(new SleepTwoSecondsTask()));
    try {
      for (int i = 0; i < 10; i++) {
        System.out.printf(
            "Main: Result for Task %d : %s%n", i, results.get(i).get());
        if (i == FIVE)
          myExecutor.shutdown();
      }
      myExecutor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e);
    }
    System.out.printf("Main: End of the program.%n");
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void shutdown() {
    System.out.printf("CustomExecutor: Going to shutdown.%n");
    System.out.printf("CustomExecutor: Executed tasks: %d%n",
                      getCompletedTaskCount());
    System.out.printf("CustomExecutor: Running tasks: %d%n", getActiveCount());
    System.out.printf("CustomExecutor: Pending tasks: %d%n", getQueue().size());
    super.shutdown();
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public List<Runnable> shutdownNow() {
    System.out.printf("CustomExecutor: Going to immediately shutdown.%n");
    System.out.printf("CustomExecutor: Executed tasks: %d%n",
                      getCompletedTaskCount());
    System.out.printf("CustomExecutor: Running tasks: %d%n", getActiveCount());
    System.out.printf("CustomExecutor: Pending tasks: %d%n", getQueue().size());
    return super.shutdownNow();
  }

  @Override
  protected void beforeExecute(Thread t, Runnable r) {
    System.out.printf("CustomExecutor: A task is beginning: %s : %s%n",
                      t.getName(),
                      r.hashCode());
    startTimes.put(String.valueOf(r.hashCode()), new Date());
  }

  @Override
  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.UnusedAssignment"})
  protected void afterExecute(Runnable r, Throwable t) {
    Future<?> result = (Future<?>)r;
    try {
      System.out.printf("*********************************%n");
      System.out.printf("CustomExecutor: A task is finishing.%n");
      System.out.printf("CustomExecutor: Result: %s%n", result.get());
      Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
      Date finishDate = new Date();
      long diff = finishDate.getTime() - startDate.getTime();
      System.out.printf("CustomExecutor: Duration: %d%n", diff);
      System.out.printf("*********************************%n");
    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  static class SleepTwoSecondsTask implements Callable<String> {
    @Override
    public String call() throws Exception {
      TimeUnit.SECONDS.sleep(2);
      return new Date().toString();
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof CustomExecutor))
      return false;
    CustomExecutor other = (CustomExecutor)o;
    if (!other.canEqual((Object)this))
      return false;
    if (!super.equals(o))
      return false;
    Object this$startTimes = this.startTimes;
    Object other$startTimes = other.startTimes;
    if (this$startTimes == null ? other$startTimes != null
                                : !this$startTimes.equals(other$startTimes))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof CustomExecutor;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = super.hashCode();
    Object $startTimes = this.startTimes;
    result =
        result * PRIME + ($startTimes == null ? 43 : $startTimes.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "CustomExecutor(startTimes=" + this.startTimes + ")";
  }
}
