package threads;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public enum MultiSemaphoreExample {
  ;

  public static void main(String[] args) {
    PrintQueue printQueue = new PrintQueue();
    Thread[] thread = new Thread[10];
    for (int i = 0; i < 10; i++) thread[i] = new Thread(new Job(printQueue), "Thread" + i);

    for (int i = 0; i < 10; i++) thread[i].start();
  }

  static class PrintQueue {
    private final Semaphore semaphore;
    private boolean[] freePrinters;
    private Lock lockPrinters;

    PrintQueue() {
      freePrinters = new boolean[] {true, true, true};
      Random random = new Random();
      boolean fair = random.nextBoolean();
      lockPrinters = new ReentrantLock(fair);
      semaphore = new Semaphore(3, random.nextBoolean());
      System.out.println("Semaphore fair: " + semaphore.isFair());
      System.out.println("ReentrantLock fair: " + fair);
    }

    public void printJob(Object document) {
      try {
        semaphore.acquire();
        int assignedPrinter = getPrinter();
        long duration = (long) (Math.random() * 10);
        System.out.printf(
            "%s: PrintQueue: Printing a Job at %d utilizing %d seconds\n",
            Thread.currentThread().getName(), assignedPrinter, duration);
        Thread.sleep(duration);
        freePrinters[assignedPrinter] = true;
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        semaphore.release();
      }
    }

    private int getPrinter() {
      int ret = -1;
      try {
        lockPrinters.lock();
        for (int i = 0; i < freePrinters.length; i++) {
          if (freePrinters[i]) {
            ret = i;
            freePrinters[i] = false;
            break;
          }
        }
      } finally {
        lockPrinters.unlock();
      }
      return ret;
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
      System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
      printQueue.printJob(new Object());
      System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
  }
}
