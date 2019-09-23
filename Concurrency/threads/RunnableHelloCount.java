package threads;

public class RunnableHelloCount implements Runnable {
  private Thread thread1;
  private Thread thread2;

  public RunnableHelloCount() {
    // Since current object implements Runnable, it can
    // be used as the argument to the Thread
    // constructor for each of the member threads…
    thread1 = new Thread(this);
    thread2 = new Thread(this);
    thread1.start();
    thread2.start();
  }

  public static void main(String[] args) {
    RunnableHelloCount threadDemo = new RunnableHelloCount();
  }

  public void run() {
    int pause;
    for (int i = 0; i < 10; i++) {
      try {
        System.out.println(Thread.currentThread().getName()
                           + " being executed.");
        pause = (int)(Math.random() * 3000);
        Thread.sleep(pause);
      } catch (InterruptedException interruptEx) {
        System.out.println(interruptEx);
        Thread.currentThread().interrupt();
      }
    }
  }
}
