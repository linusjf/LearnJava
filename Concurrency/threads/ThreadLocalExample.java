package threads;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public enum ThreadLocalExample {
  ;

  public static void main(String[] args) {
    try {

      Thread thread1 = new Thread(new MyRunnable());
      Thread thread2 = new Thread(new MyRunnable());

      thread1.start();
      thread2.start();

      thread1.join();

      // wait for thread 1 to terminate
      thread2.join();
      // wait for thread 2 to terminate
    } catch (InterruptedException ex) {
      System.err.println(ex);
    }
  }

  public static class MyRunnable implements Runnable {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private final Random random = new Random();

    @Override
    public void run() {
      threadLocal.set(random.nextInt(100));

      try {
        TimeUnit.MILLISECONDS.sleep(2000);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
      System.out.println(threadLocal.get());
    }
  }
}
