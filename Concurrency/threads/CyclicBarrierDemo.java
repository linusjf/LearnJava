package threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
  private CyclicBarrier cyclicBarrier;
  private final List<List<Integer>> partialResults =
      Collections.synchronizedList(new ArrayList<>());
  private final Random random = new Random();
  private int numPartialResults;
  private final int numWorkers;
  private CountDownLatch latch;

  public CyclicBarrierDemo(int numWorkers) {
    this.numWorkers = numWorkers;
  }

  public void runSimulation(int numberOfPartialResults) {
    numPartialResults = numberOfPartialResults;
    latch = new CountDownLatch(numWorkers + 1);

    cyclicBarrier = new CyclicBarrier(numWorkers, new AggregatorThread());

    System.out.println(
        "Spawning "
            + numWorkers
            + " worker threads to compute "
            + numPartialResults
            + " partial results each");

    for (int i = 0; i < numWorkers; i++) {
      Thread worker = new Thread(new NumberCruncherThread());
      worker.setName("Thread " + i);
      worker.start();
    }
    try {
      latch.await();
      cyclicBarrier.reset();

      latch = new CountDownLatch(numWorkers + 1);
      partialResults.clear();
      for (int i = 0; i < numWorkers; i++) {
        Thread worker = new Thread(new NumberCruncherThread());
        worker.setName("Thread " + i);
        worker.start();
      }
    } catch (InterruptedException ex) {
      System.err.println(ex);
    }
  }

  public static void main(String[] args) {
    CyclicBarrierDemo demo = new CyclicBarrierDemo(5);
    demo.runSimulation(3);
  }

  class NumberCruncherThread implements Runnable {
    @Override
    public void run() {
      String thisThreadName = Thread.currentThread().getName();
      List<Integer> partialResult = new ArrayList<>();

      // Crunch some numbers and store the partial result
      for (int i = 0; i < numPartialResults; i++) {
        Integer num = random.nextInt(10);
        System.out.println(thisThreadName + ": Crunching some numbers! Final result - " + num);
        partialResult.add(num);
      }

      partialResults.add(partialResult);
      try {
        System.out.println(thisThreadName + " waiting for others to reach barrier.");
        latch.countDown();
        cyclicBarrier.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        System.err.println(e);
      }
    }
  }

  class AggregatorThread implements Runnable {
    @Override
    public void run() {
      String thisThreadName = Thread.currentThread().getName();

      System.out.println(
          thisThreadName
              + ": Computing sum of "
              + numWorkers
              + " workers, having "
              + numPartialResults
              + " results each.");
      int sum = 0;

      for (List<Integer> threadResult : partialResults) {
        System.out.print("Adding ");
        for (Integer partialResult : threadResult) {
          System.out.print(partialResult + " ");
          sum += partialResult;
        }
        System.out.println();
      }
      System.out.println(thisThreadName + ": Final result = " + sum);
      latch.countDown();
    }
  }
}
