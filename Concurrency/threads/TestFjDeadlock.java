package threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class TestFjDeadlock {

  private final static int[] intArray = new int[256 * 1024];
  private final static float[] floatArray = new float[256 * 1024];

  private final static int THREADS = 1;
  private final static int TASKS = 16;
  private final static int ITERATIONS = 10000;

  public static void main(String[] args) {

    // Initialize the array
    for (int i = 0; i < intArray.length; i++) {
      intArray[i] = i;
    }

    ForkJoinPool pool = new ForkJoinPool(THREADS);

    // Run through ITERATIONS loops, subdividing the iteration into TASKS F-J
    // subtasks
    for (int i = 0; i < ITERATIONS; i++) {
      pool.invoke(new RecursiveIterate(0, intArray.length));
    }

    pool.shutdown();
  }

  private static class RecursiveIterate extends RecursiveAction {

    final int start;
    final int end;

    public RecursiveIterate(final int start, final int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    protected void compute() {

      if ((end - start) <= (intArray.length / TASKS)) {
        // We've reached the subdivision limit - iterate over the arrays
        for (int i = start; i < end; i += 3) {
          floatArray[i] += i + intArray[i];
        }

      } else {
        // Subdivide and start new tasks
        final int mid = (start + end) >>> 1;
        invokeAll(new RecursiveIterate(start, mid),
                  new RecursiveIterate(mid, end));
      }
    }
  }
}
