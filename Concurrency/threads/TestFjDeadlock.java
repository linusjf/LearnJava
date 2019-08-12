package threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public final class TestFjDeadlock {

  private static final int[] INT_ARRAY = new int[256 * 1024];
  private static final float[] FLOAT_ARRAY = new float[256 * 1024];

  private static final int THREADS = 1;
  private static final int TASKS = 16;
  private static final int ITERATIONS = 10_000;

  private TestFjDeadlock() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {

    // Initialize the array
    for (int i = 0; i < INT_ARRAY.length; i++)
      INT_ARRAY[i] = i;

    ForkJoinPool pool = new ForkJoinPool(THREADS);

    // Run through ITERATIONS loops, subdividing the iteration into TASKS F-J
    // subtasks
    for (int i = 0; i < ITERATIONS; i++)
      pool.invoke(new RecursiveIterate(0, INT_ARRAY.length));

    pool.shutdown();
  }

  private static class RecursiveIterate extends RecursiveAction {

    private static final long serialVersionUID = 1L;
    final int start;
    final int end;

    RecursiveIterate(final int start, final int end) {
      super();
      this.start = start;
      this.end = end;
    }

    @Override
    protected void compute() {
      if ((end - start) <= (INT_ARRAY.length / TASKS)) {
        // We've reached the subdivision limit - iterate over the arrays
        for (int i = start; i < end; i += 3)
          FLOAT_ARRAY[i] += i + INT_ARRAY[i];
      } else {
        // Subdivide and start new tasks
        final int mid = (start + end) >>> 1;
        invokeAll(new RecursiveIterate(start, mid),
                  new RecursiveIterate(mid, end));
      }
    }
  }
}
