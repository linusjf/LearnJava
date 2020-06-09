package threads;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierArchetype implements Runnable {
  private static final int NUMBER_OF_THREADS = 5;
  private static AtomicInteger counter = new AtomicInteger();
  private static Random random = new Random(System.currentTimeMillis());
  private static final CyclicBarrier BARRIER =
      new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
          counter.incrementAndGet();
        }
      });

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    ExecutorService executorService =
        Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    for (int i = 0; i < NUMBER_OF_THREADS; i++) {
      executorService.execute(new CyclicBarrierArchetype());
    }
    executorService.shutdown();
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void run() {
    try {
      while (counter.get() < 3) {
        int randomSleepTime = random.nextInt(10_000);
        System.out.println("[" + Thread.currentThread().getName()
                           + "] Sleeping for " + randomSleepTime);
        TimeUnit.MILLISECONDS.sleep(randomSleepTime);
        System.out.println("[" + Thread.currentThread().getName()
                           + "] Waiting for barrier.");
        BARRIER.await();
        System.out.println("[" + Thread.currentThread().getName()
                           + "] Finished.");
      }
    } catch (InterruptedException | BrokenBarrierException e) {
      System.err.println(e);
    }
  }
}
