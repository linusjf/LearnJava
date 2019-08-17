package threads;

import java.util.LinkedList;
import java.util.List;

public class SimpleBlockingQueue<T> {
  private List<T> queue = new LinkedList<T>();

  public int getSize() {
    synchronized (queue) {
      return queue.size();
    }
  }

  public void put(T obj) {
    synchronized (queue) {
      queue.add(obj);
      queue.notify();
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
