package threads;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    public void printJob(Object document) {
      queueLock.lock();
      try {
        Long duration = (long)(Math.random() * 10_000);
        System.out.println(Thread.currentThread().getName()
                           + ": PrintQueue: Printing a Job during "
                           + (duration / 1000) + " seconds");
        TimeUnit.MILLISECONDS.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        queueLock.unlock();
      }
      queueLock.lock();
      try {
        Long duration = (long)(Math.random() * 10_000);
        System.out.println(Thread.currentThread().getName()
                           + ": PrintQueue: Printing a Job during "
                           + (duration / 1000) + " seconds");
        TimeUnit.MILLISECONDS.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        queueLock.unlock();
      }
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
      printQueue.printJob(new Object());
      System.out.printf("%s: The document has been printed%n",
                        Thread.currentThread().getName());
    }
  }
}
