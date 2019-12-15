package collections;

import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;

public enum PriorityQueueDemo {
  ;
  public static void main(String[] args) {
    PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
    Thread[] taskThreads = new Thread[5];
    for (int i = 0; i < taskThreads.length; i++) {
      Task task = new Task(i, queue);
      taskThreads[i] = new Thread(task);
    }
    for (Thread taskThread : taskThreads) taskThread.start();

    for (Thread taskThread : taskThreads) {
      try {
        taskThread.join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("Main: Queue Size: %d%n", queue.size());
    for (int i = 0; i < taskThreads.length * 1000; i++) {
      Event event = queue.poll();
      System.out.printf(
        "Thread %s: Priority %d%n",
        event.getThread(),
        event.getPriority()
      );
    }
    System.out.printf("Main: Queue Size: %d%n", queue.size());
    System.out.printf("Main: End of the program%n");
  }

  static class Event implements Comparable<Event> {
    private final int thread;
    private final int priority;

    Event(int thread, int priority) {
      this.thread = thread;
      this.priority = priority;
    }

    public int getThread() {
      return thread;
    }

    public int getPriority() {
      return priority;
    }

    @Override
    public int compareTo(Event e) {
      if (this.priority > e.getPriority()) return -1;
      if (this.priority < e.getPriority()) return 1;
      return 0;
    }

    @Override
    public int hashCode() {
      return Objects.hash(thread, priority);
    }

    @Override
    public boolean equals(Object o) {
      // If the object is compared with itself then return true
      if (o == this) return true;

      /* Check if o is an instance of Event or not

       "null instanceof [type]" also returns false */
      if (!(o instanceof Event)) return false;

      // typecast o to Event so that we can compare data members
      Event e = (Event) o;

      // Compare the data members and return accordingly
      return e.thread == thread && e.priority == priority;
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Task implements Runnable {
    private final int id;
    private final PriorityBlockingQueue<Event> queue;

    Task(int id, PriorityBlockingQueue<Event> queue) {
      this.id = id;
      this.queue = queue;
    }

    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        Event event = new Event(id, i);
        queue.add(event);
      }
    }
  }
}
