package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Solver {
  final int N;
  final float[][] data;
  final CyclicBarrier barrier;

  public Solver(float[][] matrix)  {
    data = matrix;
    N = matrix.length;
    Runnable barrierAction = () -> mergeRows();
    barrier = new CyclicBarrier(N, barrierAction);
  }

  void solve() throws InterruptedException {
    List<Thread> threads = new ArrayList<>(N);
    for (int i = 0; i < N; i++) {
      Thread thread = new Thread(new Worker(i));
      threads.add(thread);
      thread.start();
    }
    // wait until done
    for (Thread thread: threads)
      thread.join((long)N * 1000 * 60);
  }

  void mergeRows() {
    System.out.println("Merging rows....");
  }

  class Worker implements Runnable {
    int myRow;

    Worker(int row) {
      myRow = row;
    }

    public void run() {
      processRow(myRow);
      try {
        barrier.await(1, TimeUnit.MINUTES);
      } catch (InterruptedException ex) {
        return;
      } catch (BrokenBarrierException ex) {
        return;
      } catch (TimeoutException ex) {
        return;
      }
    }

    void processRow(int row) {
      System.out.println("Processing row ..." + row);
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Solver))
      return false;
    Solver other = (Solver)o;
    if (!other.canEqual((Object)this))
      return false;
    if (this.N != other.N)
      return false;
    if (!java.util.Arrays.deepEquals(this.data, other.data))
      return false;
    Object this$barrier = this.barrier;
    Object other$barrier = other.barrier;
    if (this$barrier == null ? other$barrier != null
                             : !this$barrier.equals(other$barrier))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Solver;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.N;
    result = result * PRIME + java.util.Arrays.deepHashCode(this.data);
    Object $barrier = this.barrier;
    result = result * PRIME + ($barrier == null ? 43 : $barrier.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Solver(N=" + this.N
        + ", data=" + java.util.Arrays.deepToString(this.data)
        + ", barrier=" + this.barrier + ")";
  }
}
