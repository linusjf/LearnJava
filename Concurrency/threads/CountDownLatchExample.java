package threads;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample implements Runnable {
  private static final int NUMBER_OF_THREADS = 5;
  private static final CountDownLatch LATCH = new CountDownLatch(NUMBER_OF_THREADS);
  private static Random random = new Random(System.currentTimeMillis());

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    for (int i = 0; i < NUMBER_OF_THREADS; i++)
      executorService.execute(new CountDownLatchExample());
    executorService.shutdown();
  }

  @Override
  public void run() {
    try {
      int randomSleepTime = random.nextInt(20_000);
      System.out.println(
          "[" + Thread.currentThread().getName() + "]-Sleeping for " + randomSleepTime);
      Thread.sleep(randomSleepTime);
      LATCH.countDown();
      System.out.println("[" + Thread.currentThread().getName() + "]-Waiting for latch.");
      LATCH.await();
      System.out.println("[" + Thread.currentThread().getName() + "]-Finished.");
    } catch (InterruptedException e) {
      System.err.println(e);
    }
  }
}
