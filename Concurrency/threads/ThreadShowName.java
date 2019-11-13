package threads;

public class ThreadShowName extends Thread {

  public static void main(String[] args) {
    ThreadShowName thread1 = new ThreadShowName();
    ThreadShowName thread2 = new ThreadShowName();
    thread1.start();

    // Will call run.
    thread2.start();
    // Will call run.
  }

  public void run() {
    int pause;
    for (int i = 0; i < 10; i++) {
      try {
        System.out.println(getName() + " being executed.");
        pause = (int)(Math.random() * 3000);
        sleep(pause);
        // 0-3 seconds.
      } catch (InterruptedException interruptEx) {
        System.out.println(interruptEx);
        interrupt();
      }
    }
  }
}
