package threads;

import java.util.Random;
import java.util.concurrent.Semaphore;

public enum SemaphoreExample {
  ;

  public static void main(String[] args) {
    PrintQueue printQueue = new PrintQueue();
    Thread[] thread = new Thread[10];
    for (int i = 0; i < 10; i++) thread[i] = new Thread(new Job(printQueue), "Thread" + i);

    for (int i = 0; i < 10; i++) thread[i].start();
  }

  static class PrintQueue {
    private final Semaphore semaphore;

    PrintQueue() {
      semaphore = new Semaphore(1, new Random().nextBoolean());
      System.out.println("Semaphore fair: " + semaphore.isFair());
    }

    public void printJob(Object document) {
      try {
        semaphore.acquire();
        long duration = (long) (Math.random() * 10);
        System.out.printf(
            "%s: PrintQueue: Printing a Job utilizing %d seconds\n",
            Thread.currentThread().getName(), duration);
        Thread.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        semaphore.release();
      }
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Job implements Runnable {
    private final PrintQueue printQueue;

    Job(PrintQueue printQueue) {
      this.printQueue = printQueue;
    }

    @Override
    public void run() {
      System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
      printQueue.printJob(new Object());
      System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
  }
}
