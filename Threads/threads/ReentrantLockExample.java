package threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public enum ReentrantLockExample {
  ;

  public static void main(String[] args) {
    PrintQueue printQueue = new PrintQueue();
    Thread[] thread = new Thread[10];
    for (int i = 0; i < 10; i++)
      thread[i] = new Thread(new Job(printQueue), "Thread " + i);
    for (int i = 0; i < 10; i++)
      thread[i].start();
  }

  static class PrintQueue {
    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document) {
      queueLock.lock();
      try {
        Long duration = (long)(Math.random() * 10_000);
        System.out.println(Thread.currentThread().getName()
                           + ": PrintQueue: Printing a Job utilizing "
                           + (duration / 1000) + " seconds");
        Thread.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        queueLock.unlock();
      }
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Job implements Runnable {

    private PrintQueue printQueue;

    Job(PrintQueue printQueue) {
      this.printQueue = printQueue;
    }

    @Override
    public void run() {
      System.out.printf("%s: Going to print a document\n",
                        Thread.currentThread().getName());
      printQueue.printJob(new Object());
      System.out.printf("%s: The document has been printed\n",
                        Thread.currentThread().getName());
    }
  }
}
