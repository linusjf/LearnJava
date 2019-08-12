package threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;


public class SemaphoreArchetype implements Runnable {
  private static final Semaphore semaphore = new Semaphore(3, true);
  private static final AtomicInteger counter = new AtomicInteger();
  private static final long endMillis = System.currentTimeMillis() + 1000;

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 5; i++) 
      executorService.execute(new SemaphoreArchetype());
    executorService.shutdown();
  }
  
  public void run() {
    while (System.currentTimeMillis() < endMillis) {
      try {
        semaphore.acquire();
      } catch (InterruptedException e) {
        System.out.println("[" + Thread.currentThread().getName()
                           + "]←-Interrupted in acquire().");
      }
      int counterValue = counter.incrementAndGet();
      System.out.println("[" + Thread.currentThread().getName()
                         + "]←-semaphore acquired: " + counterValue);
      if (counterValue > 3) {
        throw new IllegalStateException(
            "More than three threads←-acquired the lock.");
      }
      counter.decrementAndGet();
      semaphore.release();
    }
  }
}
