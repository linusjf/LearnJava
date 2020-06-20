package threads;

import java.util.LinkedList;
import java.util.List;

public class SimpleBlockingQueue<T> {
  private final List<T> queue = new LinkedList<>();

  public int getSize() {
    synchronized (queue) {
      return queue.size();
    }
  }

  public void put(T obj) {
    synchronized (queue) {
      queue.add(obj);
      queue.notifyAll();
    }
  }

  public T get() throws InterruptedException {
    synchronized (queue) {
      while (queue.isEmpty()) queue.wait(100);
      return queue.remove(0);
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof SimpleBlockingQueue)) return false;
    SimpleBlockingQueue<?> other = (SimpleBlockingQueue<?>) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$queue = this.queue;
    Object other$queue = other.queue;
    if (this$queue == null ? other$queue != null : !this$queue.equals(other$queue)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof SimpleBlockingQueue;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $queue = this.queue;
    result = result * PRIME + ($queue == null ? 43 : $queue.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "SimpleBlockingQueue(queue=" + this.queue + ")";
  }
}
