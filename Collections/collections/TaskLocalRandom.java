package collections;

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements Runnable {

  public TaskLocalRandom() {
    ThreadLocalRandom.current();
  }

  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    for (int i = 0; i < 10; i++) {
      System.out.printf("%s with priority %d: %d \n",
                        name,
                        Thread.currentThread().getPriority(),
                        ThreadLocalRandom.current().nextInt(10));
    }
  }

  public static void main(String[] args) {
    Thread[] threads = new Thread[3];
    for (int i = 0; i < 3; i++) {
      TaskLocalRandom task = new TaskLocalRandom();
      threads[i] = new Thread(task);
      threads[i].setPriority(ThreadLocalRandom.current().nextInt(10) + 1);
    }
    for (Thread thread: threads)
      thread.start();
  }
}
