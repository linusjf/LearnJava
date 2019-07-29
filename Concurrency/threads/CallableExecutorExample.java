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

  public static void main(String[] args) {
    ThreadPoolExecutor executor =
        (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
    List<Future<Integer>> resultList = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      Integer number = random.nextInt(10);
      FactorialCalculator calculator = new FactorialCalculator(number);
      Future<Integer> result = executor.submit(calculator);
      resultList.add(result);
    }
    do {
      System.out.printf("Main: Number of Completed Tasks: %d\n",
                        executor.getCompletedTaskCount());
      for (int i = 0; i < resultList.size(); i++) {
        Future<Integer> result = resultList.get(i);
        System.out.printf("Main: Task %d completed: %s\n", i, result.isDone());
      }
      try {
        TimeUnit.MILLISECONDS.sleep(50);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    } while (executor.getCompletedTaskCount() < resultList.size());
    System.out.printf("Main: Results\n");
    for (int i = 0; i < resultList.size(); i++) {
      Future<Integer> result = resultList.get(i);
      Integer number = null;
      try {
        number = result.get();
      } catch (InterruptedException | ExecutionException e) {
        System.err.println(e);
      }
      System.out.printf("Main: Task %d: calculated factorial: %d\n", i, number);
    }
    executor.shutdown();
  }

  static class FactorialCalculator implements Callable<Integer> {
    private Integer number;

    FactorialCalculator(Integer number) {
      this.number = number;
    }

    @Override
    public Integer call() throws Exception {
      int result = 1;
      if (number == 0 || number == 1) {
        result = 1;
      } else {
        for (int i = 2; i <= number; i++) {
          result *= i;
          TimeUnit.MILLISECONDS.sleep(20);
        }
      }
      System.out.printf("%s: Factorial %d:  %d\n",
                        Thread.currentThread().getName(),
                        number,
                        result);
      return result;
    }
  }
}
