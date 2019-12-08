package threads;

import java.util.Random;

public class RunnableHelloCount implements Runnable {

  private final Random random = new Random();

  Thread thread1 = new Thread(this);
  Thread thread2 = new Thread(this);

  public RunnableHelloCount() {
    // empty constructor
  }

  public void start() {
    thread1.start();
    thread2.start();
  }

  public static void main(String[] args) {
    new RunnableHelloCount().start();
  }

  @Override
  public void run() {
    int pause;
    for (int i = 0; i < 10; i++) {
      try {
        System.out.println(Thread.currentThread().getName()
                           + " being executed.");
        pause = random.nextInt(3000);
        Thread.sleep(pause);
      } catch (InterruptedException interruptEx) {
        System.out.println(interruptEx);
        Thread.currentThread().interrupt();
      }
    }
  }
}
