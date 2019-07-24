package threads;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public enum CleanerDaemon {
  ;

  public static void main(String[] args) {
    Deque<Event> deque = new ArrayDeque<>();
    WriterTask writer = new WriterTask(deque);
    for (int i = 0; i < 3; i++) {
      Thread thread = new Thread(writer);
      thread.start();
    }
    CleanerTask cleaner = new CleanerTask(deque);
    cleaner.start();
  }

  static class Event {

    String evt;
    Date date;

    String getEvent() {
      return evt;
    }

    void setEvent(String event) {
      this.evt = event;
    }

    void setDate(Date date) {
      this.date = date;
    }

    Date getDate() {
      return date;
    }
  }

  static class WriterTask implements Runnable {
    private Deque<Event> deque;
    WriterTask(Deque<Event> deque) {
      this.deque = deque;
    }

    @Override
    public void run() {
      for (int i = 1; i < 100; i++) {
        Event event = new Event();
        event.setDate(new Date());
        event.setEvent(String.format("The thread %s has generated an event",
                                     Thread.currentThread().getId()));
      synchronized(deque) {
        deque.addFirst(event);
      }
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      }
    }
  }

  static class CleanerTask extends Thread {
    private Deque<Event> deque;

    CleanerTask(Deque<Event> deque) {
      super();
      this.deque = deque;
      setDaemon(true);
    }

    @Override
    public void run() {
      while (true) {
        Date date = new Date();
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
        clean(date);
      }
    }

    private void clean(Date date) {

      if (deque.size() == 0) {
        return;
      }
      boolean delete = false;
      Event e = deque.getLast();
      long difference = date.getTime() - e.getDate().getTime();
      while (difference > 10_000 && !deque.isEmpty())  {
          System.out.printf("Cleaner: %s\n", e.getEvent());
          deque.removeLast();
          delete = true;
          e = deque.getLast();
      difference = date.getTime() - e.getDate().getTime();
        }
      if (delete) {
        System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
      }
    }
  }
}
