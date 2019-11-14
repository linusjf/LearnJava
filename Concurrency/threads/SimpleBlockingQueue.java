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
    while (true) {
      synchronized (queue) {
        if (queue.isEmpty()) {
          queue.wait();
        } else {
          return queue.remove(0);
        }
      }
    }
  }
}
