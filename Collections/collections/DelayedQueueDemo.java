package collections;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public enum DelayedQueueDemo {
  ;

  public static void main(String[] args) {
    try {
      DelayQueue<Event> queue = new DelayQueue<>();
      Thread[] threads = new Thread[5];
      startThreads(threads, queue);
      for (Thread thread: threads)
        thread.join();
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void startThreads(Thread[] threads, DelayQueue<Event> queue) {
    for (int i = 0; i < threads.length; ++i) {
      Task task = new Task(i + 1, queue);
      threads[i] = new Thread(task);
      threads[i].start();
    }
  }

  @SuppressWarnings("PMD.BeanFieldsShouldSerialize")
  static class Event implements Delayed {
    private final Date startDate;

    Event(Date startDate) {
      if (startDate == null)
        throw new IllegalArgumentException(
            "Null date arg in constructor of class: " + getClass());
      this.startDate = startDate;
    }

    @Override
    public int compareTo(Delayed o) {
      long result = this.getDelay(TimeUnit.NANOSECONDS)
                    - o.getDelay(TimeUnit.NANOSECONDS);
      if (result < 0)
        return -1;
      if (result > 0)
        return 1;
      return 0;
    }

    @Override
    public int hashCode() {
      return Objects.hash(startDate);
    }

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public boolean equals(Object o) {
      // If the object is compared with itself then return true
      if (o == this)
        return true;

      /* Check if o is an instance of Complex or not

      "null instanceof [type]" also returns false */
      if (!(o instanceof Event))
        return false;

      // typecast o to Event so that we can compare data members
      Event e = (Event)o;

      // Compare the data members and return accordingly
      return e.startDate.equals(startDate);
    }

    @Override
    public long getDelay(TimeUnit unit) {
      Date now = new Date();
      long diff = startDate.getTime() - now.getTime();
      return unit.convert(diff, TimeUnit.MILLISECONDS);
    }
  }

  @SuppressWarnings({"PMD.ShortClassName", "PMD.BeanFieldsShouldSerialize"})
  static class Task implements Runnable {
    private final int id;
    private final DelayQueue<Event> queue;

    Task(int id, DelayQueue<Event> queue) {
      this.id = id;
      this.queue = queue;
    }

    @Override
    public void run() {
      Date now = new Date();
      Date delay = new Date();
      delay.setTime(now.getTime() + (id * 1000L));
      System.out.printf("Thread %s: %s%n", id, delay);
      for (int i = 0; i < 100; i++) {
        Event event = new Event(delay);
        queue.add(event);
      }
    }
  }
}
