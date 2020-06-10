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
    System.out.println("Spawning " + numWorkers + " worker threads to compute "
                       + numPartialResults + " partial results each");
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
    @SuppressWarnings("PMD.LawOfDemeter")
    public void run() {
      String thisThreadName = Thread.currentThread().getName();
      List<Integer> partialResult = new ArrayList<>(numPartialResults);
      // Crunch some numbers and store the partial result
      for (int i = 0; i < numPartialResults; i++) {
        Integer num = random.nextInt(10);
        System.out.println(thisThreadName
                           + ": Crunching some numbers! Final result - " + num);
        partialResult.add(num);
      }
      partialResults.add(partialResult);
      try {
        System.out.println(thisThreadName
                           + " waiting for others to reach barrier.");
        latch.countDown();
        cyclicBarrier.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        System.err.println(e);
      }
    }
  }

  class AggregatorThread implements Runnable {
    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void run() {
      String thisThreadName = Thread.currentThread().getName();
      System.out.println(thisThreadName + ": Computing sum of " + numWorkers
                         + " workers, having " + numPartialResults
                         + " results each.");
      int sum = 0;
      for (List<Integer> threadResult: partialResults) {
        System.out.print("Adding ");
        for (Integer partialResult: threadResult) {
          System.out.print(partialResult + " ");
          sum += partialResult;
        }
        System.out.println();
      }
      System.out.println(thisThreadName + ": Final result = " + sum);
      latch.countDown();
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof CyclicBarrierDemo))
      return false;
    CyclicBarrierDemo other = (CyclicBarrierDemo)o;
    if (!other.canEqual((Object)this))
      return false;
    Object this$cyclicBarrier = this.cyclicBarrier;
    Object other$cyclicBarrier = other.cyclicBarrier;
    if (this$cyclicBarrier == null
            ? other$cyclicBarrier != null
            : !this$cyclicBarrier.equals(other$cyclicBarrier))
      return false;
    Object this$partialResults = this.partialResults;
    Object other$partialResults = other.partialResults;
    if (this$partialResults == null
            ? other$partialResults != null
            : !this$partialResults.equals(other$partialResults))
      return false;
    Object this$random = this.random;
    Object other$random = other.random;
    if (this$random == null ? other$random != null
                            : !this$random.equals(other$random))
      return false;
    if (this.numPartialResults != other.numPartialResults)
      return false;
    if (this.numWorkers != other.numWorkers)
      return false;
    Object this$latch = this.latch;
    Object other$latch = other.latch;
    if (this$latch == null ? other$latch != null
                           : !this$latch.equals(other$latch))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof CyclicBarrierDemo;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $cyclicBarrier = this.cyclicBarrier;
    result = result * PRIME
             + ($cyclicBarrier == null ? 43 : $cyclicBarrier.hashCode());
    Object $partialResults = this.partialResults;
    result = result * PRIME
             + ($partialResults == null ? 43 : $partialResults.hashCode());
    Object $random = this.random;
    result = result * PRIME + ($random == null ? 43 : $random.hashCode());
    result = result * PRIME + this.numPartialResults;
    result = result * PRIME + this.numWorkers;
    Object $latch = this.latch;
    result = result * PRIME + ($latch == null ? 43 : $latch.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "CyclicBarrierDemo(cyclicBarrier=" + this.cyclicBarrier
        + ", partialResults=" + this.partialResults + ", random=" + this.random
        + ", numPartialResults=" + this.numPartialResults
        + ", numWorkers=" + this.numWorkers + ", latch=" + this.latch + ")";
  }
}
