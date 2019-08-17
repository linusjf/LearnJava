package threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to demonstrate how to use CyclicBarrier in Java. CyclicBarrier is a new Concurrency
 * Utility added in Java 5 Concurrent package.
 *
 * @author Javin Paul
 */
public enum CyclicBarrierExample {
  ;

  public static void main(String[] args) {

    // creating CyclicBarrier with 3 parties i.e. 3 Threads needs to call
    // await()
    final CyclicBarrier cb =
        new CyclicBarrier(
            3,
            new Runnable() {
              @Override
              public void run() {
                // This task will be executed once all thread reaches barrier
                System.out.println("All parties are arrived at barrier, lets play");
              }
            });

    // starting each of thread
    Thread t1 = new Thread(new Task(cb), "Thread 1");
    Thread t2 = new Thread(new Task(cb), "Thread 2");
    Thread t3 = new Thread(new Task(cb), "Thread 3");

    t1.start();
    t2.start();
    t3.start();
  }

  // Runnable task for each thread
  @SuppressWarnings("PMD.ShortClassName")
  private static class Task implements Runnable {

    private CyclicBarrier barrier;

    Task(CyclicBarrier barrier) {
      this.barrier = barrier;
    }

    @Override
    public void run() {
      try {
        System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
        barrier.await();
        System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
      } catch (InterruptedException | BrokenBarrierException ex) {
        Logger.getLogger(CyclicBarrierExample.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
