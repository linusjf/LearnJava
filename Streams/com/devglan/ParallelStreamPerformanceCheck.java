package com.devglan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ParallelStreamPerformanceCheck.
 *
 * @author only2dhir
 */
public enum ParallelStreamPerformanceCheck {
  ;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    List<Integer> numList = new ArrayList<>();
    for (int i = 0; i < 1000; i++)
      numList.add(i);

    // Processing sequentially
    long startTime = System.currentTimeMillis();
    numList.stream().forEach(i -> processData(i));
    long endTime = System.currentTimeMillis();
    double sequentialStreamTimetaken = (endTime - startTime) / 1000D;
    System.out.println("Time required with stream() : "
                       + sequentialStreamTimetaken);

    // Parallel processing
    startTime = System.currentTimeMillis();
    numList.parallelStream().forEach(i -> processData(i));
    endTime = System.currentTimeMillis();
    long parallelStreamTimetaken = (endTime - startTime) / 1000L;
    System.out.println("Time required with parallelStream() : "
                       + parallelStreamTimetaken);
    System.out.println("Differential time : "
                       + (sequentialStreamTimetaken - parallelStreamTimetaken));
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void processData(int num) {
    try {
      TimeUnit.MILLISECONDS.sleep(10);
    } catch (InterruptedException e) {
      System.err.println(e);
      Thread.currentThread().interrupt();
    }
  }
}
