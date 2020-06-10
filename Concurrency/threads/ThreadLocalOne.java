package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum ThreadLocalOne {
  ;
  private static final int THREAD_POOL_SIZE = 500;
  private static final int LIST_SIZE = 1024 * 25;

  private static ThreadLocal<List<Integer>> threadLocal = new ThreadLocal<>();
  private static ExecutorService executorService =
        Executors.newFixedThreadPool(THREAD_POOL_SIZE);

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    Runtime rt = Runtime.getRuntime();
    System.out.println("Free memory: "
                       + rt.freeMemory() / (1024 * 1024)
                       + " MB");

    for (int i = 0; i < THREAD_POOL_SIZE * 2; i++) {
      executorService.execute(() -> {
        threadLocal.set(getBigList());
        System.out.println(Thread.currentThread().getName() + " : "
                           + threadLocal.get().size());
        // threadLocal.remove();
        // explicitly remove the cache, OOM shall not occur;
      });
    }
    System.out.println("Free memory: "
                       + rt.freeMemory() / (1024 * 1024)
                       + " MB");
    executorService.shutdown();
    System.out.println("Free memory: "
                       + rt.freeMemory() / (1024 * 1024)
                       + " MB");
  }

  private static List<Integer> getBigList() {
    Random random = new Random();
    int listSize = random.nextInt(LIST_SIZE);
    List<Integer> ret = new ArrayList<>(listSize);
    for (int i = 0; i < listSize; i++) {
      ret.add(i);
    }
    return ret;
  }
}
