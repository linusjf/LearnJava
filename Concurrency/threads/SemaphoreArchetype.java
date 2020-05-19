package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreArchetype implements Runnable {
  private static final Semaphore SEMAPHORE = new Semaphore(3, true);
  private static final AtomicInteger COUNTER = new AtomicInteger();
  private static final long END_MILLIS = System.currentTimeMillis() + 1000;
  private static final int MAX_THREADS = 3;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 5; i++) executorService.execute(new SemaphoreArchetype());
    executorService.shutdown();
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void run() {
    while (System.currentTimeMillis() < END_MILLIS) {
      try {
        SEMAPHORE.acquire();
      } catch (InterruptedException e) {
        System.out.println("[" + Thread.currentThread().getName() + "]-Interrupted in acquire().");
      }
      int counterValue = COUNTER.incrementAndGet();
      System.out.println(
          "[" + Thread.currentThread().getName() + "]-SEMAPHORE acquired: " + counterValue);
      if (counterValue > MAX_THREADS) {
        throw new IllegalStateException("More than three threads -acquired the lock.");
      }
      COUNTER.decrementAndGet();
      SEMAPHORE.release();
    }
  }
}
