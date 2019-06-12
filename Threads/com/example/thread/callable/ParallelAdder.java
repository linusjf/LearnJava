package com.example.thread.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelAdder {
  private static final int NUM_COUNT = 100;

  public Integer parallelSum() {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    List<Future<Integer>> list = new ArrayList<Future<Integer>>();
    int count = 1;
    int prev = 0;
    for (int i = 0; i < NUM_COUNT; i++) {
      if (count % 2 == 0) { // grouping
        System.out.println("Prev :" + prev + " current: " + i);
        Future<Integer> future = executor.submit(new CallableAdder(prev, i));
        list.add(future);
        count = 1;
        continue;
      }
      prev = i;
      count++;
    }
    int totsum = 0;
    for (Future<Integer> fut : list) {
      try {
        totsum = totsum + fut.get();
      } catch (InterruptedException e) {
        System.err.println(e.getMessage());
      } catch (ExecutionException e) {
        System.err.println(e.getMessage());
      }
    }
    executor.shutdown();
    System.out.println("Total Sum is " + totsum);
    return totsum;
  }

  public int sequentialSum() {
    Integer totsum = 0;
    for (int i = 0; i < NUM_COUNT; i++) totsum = totsum + i;
    System.out.println("sequentialSum Total Sum is " + totsum);
    return totsum;
  }

  public static void main(String[] args) {
    ParallelAdder adder = new ParallelAdder();
    int pSum = adder.parallelSum();
    int sSum = adder.sequentialSum();
    System.out.println("parallel Sum equals  Sequential Sum ? ");
    System.out.println("Answer is :: " + (pSum == sSum));
  }
}
