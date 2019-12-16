package com.example.thread.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelAdder {
  private static final int NUM_COUNT = 100;
  private static final int NUM_THREADS = 10;

  public Integer parallelSum() {
    ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    List<Future<Integer>> list = new ArrayList<>();
    for (int i = 0; i < NUM_COUNT; i++) {
      if ((i + 1) % 2 == 0) {
        // grouping
        System.out.println("Prev :" + (i - 1) + " current: " + i);
        Future<Integer> future = submitNumbers(i - 1, i, executor);
        list.add(future);
      }
    }
    int totsum = 0;
    for (Future<Integer> fut : list) {
      try {
        totsum = totsum + fut.get();
      } catch (InterruptedException | ExecutionException e) {
        System.err.println(e.getMessage());
      }
    }
    executor.shutdown();
    System.out.println("Total Sum is " + totsum);
    return totsum;
  }

  private Future<Integer> submitNumbers(int prev, int i, ExecutorService executor) {
    return executor.submit(new CallableAdder(prev, i));
  }

  public int sequentialSum() {
    Integer totsum = 0;
    for (int i = 0; i < NUM_COUNT; i++) totsum = totsum + i;
    System.out.println("sequentialSum Total Sum is " + totsum);
    return totsum;
  }

  public static void main(String[] args) {
    ParallelAdder adder = new ParallelAdder();
    int parllSum = adder.parallelSum();
    int seqSum = adder.sequentialSum();
    System.out.println("parallel Sum equals  Sequential Sum ? ");
    System.out.println("Answer is :: " + (parllSum == seqSum));
  }
}
