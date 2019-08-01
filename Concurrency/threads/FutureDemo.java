package threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to show how to use Future in Java. Future allows to write
 * asynchronous code in Java, where Future promises result to be available in
 * future
 *
 * @author Javin
 */
public enum FutureDemo {
  ;
  private static final ExecutorService THREAD_POOL =
      Executors.newFixedThreadPool(3);

  public static void main(String[] args) {
    try {
      FactorialCalculator task = new FactorialCalculator(10);
      System.out.println("Submitting Task ...");

      Future<Long> future = THREAD_POOL.submit(task);

      System.out.println("Task is submitted");

      while (!future.isDone()) {
        System.out.println("Task is not completed yet....");
        Thread.sleep(1);  // sleep for 1 millisecond before checking again
      }

      System.out.println("Task is completed, let's check result");
      long factorial = future.get();
      System.out.println("Factorial of 10 is : " + factorial);

      THREAD_POOL.shutdown();
    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e);
    }
  }

  private static class FactorialCalculator implements Callable<Long> {

    private final int number;

    FactorialCalculator(int number) {
      this.number = number;
    }

    @Override
    public Long call() {
      long output = 0;
      try {
        output = factorial(number);
      } catch (InterruptedException ex) {
        Logger.getLogger(FutureDemo.class.getName())
            .log(Level.SEVERE, null, ex);
      }
      return output;
    }

    private long factorial(int number) throws InterruptedException {
      if (number < 0) {
        throw new IllegalArgumentException("Number must be greater than zero");
      }
      int num = number;
      long result = 1;
      while (num > 0) {
        Thread.sleep(1);  // adding delay for example
        result = result * num;
        num--;
      }
      return result;
    }
  }
}
