package threads;

import static java.util.concurrent.TimeUnit.*;

import java.util.Random;

@SuppressWarnings("PMD.SystemPrintln")
public enum ThreadLocalExample {
  ;

  public static void main(String[] args) {
    try {

      Thread thread1 = new Thread(new MyRunnable());
      Thread thread2 = new Thread(new MyRunnable());

      thread1.start();
      thread2.start();

      // wait for thread 1 to terminate
      thread1.join(2000);

      // wait for thread 2 to terminate
      thread2.join(2000);
    } catch (InterruptedException ex) {
      System.err.println(ex);
    }
  }

  public static class MyRunnable implements Runnable {
    private static final ThreadLocal<Integer> THREAD_LOCAL =
        new ThreadLocal<>();
    private final Random random = new Random();

    @Override
    public void run() {
      THREAD_LOCAL.set(random.nextInt(100));

      try {
        MILLISECONDS.sleep(2000);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
      System.out.println(THREAD_LOCAL.get());
    }
  }
}
