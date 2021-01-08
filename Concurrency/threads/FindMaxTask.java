package threads;

import java.util.Arrays;
import java.util.concurrent.Callable;

class FindMaxTask implements Callable<Integer> {
  private final int[] data;
  private final int start;
  private final int end;

  FindMaxTask(int[] data, int start, int end) {
    this.data = Arrays.copyOf(data, data.length);
    this.start = start;
    this.end = end;
  }

  @Override
  public Integer call() {
    int max = Integer.MIN_VALUE;
    for (int i = start; i < end; i++) {
      if (data[i] > max)
        max = data[i];
    }
    return max;
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof FindMaxTask))
      return false;
    FindMaxTask other = (FindMaxTask)o;
    if (!other.canEqual((Object)this))
      return false;
    if (!java.util.Arrays.equals(this.data, other.data))
      return false;
    if (this.start != other.start)
      return false;
    if (this.end != other.end)
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof FindMaxTask;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + java.util.Arrays.hashCode(this.data);
    result = result * PRIME + this.start;
    result = result * PRIME + this.end;
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "FindMaxTask(data=" + java.util.Arrays.toString(this.data)
        + ", start=" + this.start + ", end=" + this.end + ")";
  }
}
