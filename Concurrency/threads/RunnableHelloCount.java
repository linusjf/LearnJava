package threads;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RunnableHelloCount implements Runnable {
  Thread thread1 = new Thread(this);
  Thread thread2 = new Thread(this);
  private final Random random = new Random();

  public void start() {
    thread1.start();
    thread2.start();
  }

  public static void main(String[] args) {
    new RunnableHelloCount().start();
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void run() {
    int pause;
    for (int i = 0; i < 10; i++) {
      try {
        System.out.println(Thread.currentThread().getName()
                           + " being executed.");
        pause = random.nextInt(3000);
        TimeUnit.MILLISECONDS.sleep(pause);
      } catch (InterruptedException interruptEx) {
        System.out.println(interruptEx);
        Thread.currentThread().interrupt();
      }
    }
  }
}
