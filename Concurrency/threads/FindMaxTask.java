package threads;

import java.util.Arrays;
import java.util.concurrent.Callable;

class FindMaxTask implements Callable<Integer> {
  private int[] data;
  private int start;
  private int end;

  FindMaxTask(int[] data, int start, int end) {
    this.data = Arrays.copyOf(data, data.length);
    this.start = start;
    this.end = end;
  }

  @Override
  public Integer call() {
    int max = Integer.MIN_VALUE;
    for (int i = start; i < end; i++) {
      if (data[i] > max) max = data[i];
    }
    return max;
  }
}
