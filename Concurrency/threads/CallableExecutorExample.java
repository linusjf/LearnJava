package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public enum CallableExecutorExample {
  ;
  private static Random random = new Random();

  private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    List<Future<Integer>> resultList = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      Integer number = random.nextInt(10);
      FactorialCalculator calculator = new FactorialCalculator(number);
      Future<Integer> result = executor.submit(calculator);
      resultList.add(result);
    }
    do {
      System.out.printf("Main: Number of Completed Tasks: %d%n", executor.getCompletedTaskCount());
      for (int i = 0; i < resultList.size(); i++) {
        Future<Integer> result = resultList.get(i);
        System.out.printf("Main: Task %d completed: %s%n", i, result.isDone());
      }
      try {
        TimeUnit.MILLISECONDS.sleep(50);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    } while (executor.getCompletedTaskCount() < resultList.size());
    System.out.printf("Main: Results%n");
    for (int i = 0; i < resultList.size(); i++) {
      Future<Integer> result = resultList.get(i);
      try {
        Integer number = result.get();
        System.out.printf("Main: Task %d: calculated factorial: %d%n", i, number);
      } catch (InterruptedException | ExecutionException e) {
        System.err.println(e);
      }
    }
    executor.shutdown();
  }

  static class FactorialCalculator implements Callable<Integer> {
    private final Integer number;

    FactorialCalculator(Integer number) {
      this.number = number;
    }

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public Integer call() throws Exception {
      int result = 1;
      if (number > 1) {
        for (int i = 2; i <= number; i++) {
          result *= i;
          TimeUnit.MILLISECONDS.sleep(20);
        }
      }
      System.out.printf(
          "%s: Factorial %d:  %d%n", Thread.currentThread().getName(), number, result);
      return result;
    }
  }
}
