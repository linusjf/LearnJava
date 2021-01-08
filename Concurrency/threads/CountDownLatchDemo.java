package threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("PMD.SystemPrintln")
public enum CountDownLatchDemo {
  ;
  private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(5);

  private static final String DOING_WORK = "Doing some work...";

  public static void main(String[] args) {
    final Thread run1 = new Thread(() -> {
      System.out.println(DOING_WORK);

      try {
        TimeUnit.MILLISECONDS.sleep(2000);
        COUNT_DOWN_LATCH.countDown();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    });

    final Thread run2 = new Thread(() -> {
      System.out.println(DOING_WORK);

      try {
        TimeUnit.MILLISECONDS.sleep(2000);
        COUNT_DOWN_LATCH.countDown();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    });

    final Thread run3 = new Thread(() -> {
      System.out.println(DOING_WORK);

      try {
        TimeUnit.MILLISECONDS.sleep(2000);
        COUNT_DOWN_LATCH.countDown();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    });

    final Thread run4 = new Thread(() -> {
      System.out.println(DOING_WORK);

      try {
        TimeUnit.MILLISECONDS.sleep(2000);
        COUNT_DOWN_LATCH.countDown();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    });

    final Thread run5 = new Thread(() -> {
      System.out.println(DOING_WORK);

      try {
        TimeUnit.MILLISECONDS.sleep(3000);
        COUNT_DOWN_LATCH.countDown();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    });

    run1.start();
    run2.start();
    run3.start();
    run4.start();
    run5.start();

    try {
      COUNT_DOWN_LATCH.await();
    } catch (InterruptedException e) {
      System.err.println(e);
    }

    System.out.println("All tasks have finished..");
  }
}
