package threads;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("PMD.SystemPrintln")
public enum ReentrantLockExample {
  ;

  public static void main(String[] args) {
    PrintQueue printQueue = new PrintQueue();
    Thread[] thread = new Thread[10];
    Arrays.setAll(thread, i -> new Thread(new Job(printQueue), "Thread " + i));
    for (Thread t: thread) {
      t.start();
      try {
        TimeUnit.MILLISECONDS.sleep(100);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.println("Using fair lock: " + printQueue.usingFair());
  }

  static class PrintQueue {
    private final Lock queueLock =
        new ReentrantLock(new Random().nextBoolean());

    @SuppressWarnings("PMD.LawOfDemeter")
    public boolean printJob(Object document) throws InterruptedException {
      boolean completed = false;
      if (queueLock.tryLock(0, TimeUnit.SECONDS)) {
        try {
          Long duration = (long)(Math.random() * 10_000);
          System.out.println(Thread.currentThread().getName()
                             + ": PrintQueue: Printing a Job during "
                             + (duration / 1000) + " seconds");
          TimeUnit.MILLISECONDS.sleep(duration);
          completed = true;
        } catch (InterruptedException e) {
          System.err.println(e);
          Thread.currentThread().interrupt();
        } finally {
          queueLock.unlock();
        }
      }
      return completed;
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    public boolean usingFair() {
      return ((ReentrantLock)queueLock).isFair();
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Job implements Runnable {
    private final PrintQueue printQueue;

    Job(PrintQueue printQueue) {
      this.printQueue = printQueue;
    }

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void run() {
      System.out.printf("%s: Going to print a document%n",
                        Thread.currentThread().getName());
      try {
        Object obj = new Object();
        while (!printQueue.printJob(obj))
          ;
      } catch (InterruptedException ie) {
        Thread.currentThread().interrupt();
      }
      System.out.printf("%s: The document has been printed%n",
                        Thread.currentThread().getName());
    }
  }
}
