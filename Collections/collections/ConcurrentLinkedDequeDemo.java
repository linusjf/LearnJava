package collections;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

public enum ConcurrentLinkedDequeDemo {
  ;
  private static Random random = new Random();

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
    Thread[] threads = new Thread[100];
    for (int i = 0; i < threads.length; i++) {
      AddTask task = new AddTask(list);
      threads[i] = new Thread(task);
      threads[i].start();
    }
    System.out.printf("Main: %d AddTask threads have been launched%n", threads.length);
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("Main: Size of the List: %d%n", list.size());
    for (int i = 0; i < threads.length; i++) {
      PollTask task = new PollTask(list);
      threads[i] = new Thread(task);
      threads[i].start();
    }
    System.out.printf("Main: %d PollTask threads have been launched%n", threads.length);
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("Main: Size of the List: %d%n", list.size());
    alternateMain();
  }

  private static void alternateMain() {
    ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
    Thread[] threads = new Thread[100];
    Thread[] threads2 = new Thread[100];
    startThreads(threads, threads2, list);
    System.out.printf("Alternate Main: %d AddTask threads have been launched%n", threads.length);
    System.out.printf(
        "Alternate Main: %d PollTask threads have been launched simultaneously%n", threads2.length);
    System.out.printf("Alternate Main: Size of the List: %d%n", list.size());

    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    for (Thread thread : threads2) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("Alternate Main: Size of the List: %d%n", list.size());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void startThreads(
      Thread[] threads, Thread[] threads2, ConcurrentLinkedDeque<String> list) {
    for (int i = 0; i < threads.length; i++) {
      AddTask task = new AddTask(list);
      threads[i] = new Thread(task);
      threads[i].setPriority(random.nextInt(10) + 1);
      threads[i].start();
      PollTask task1 = new PollTask(list);
      threads2[i] = new Thread(task1);
      threads2[i].setPriority(random.nextInt(10) + 1);
      threads2[i].start();
    }
  }

  static class AddTask implements Runnable {
    private final ConcurrentLinkedDeque<String> list;

    AddTask(ConcurrentLinkedDeque<String> list) {
      this.list = list;
    }

    @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
    @Override
    public void run() {
      String str = Thread.currentThread().getName() + " Element : %d";
      for (int i = 0; i < 10_000; i++) 
        list.add(String.format(str, i));
    }
  }

  static class PollTask implements Runnable {
    private final ConcurrentLinkedDeque<String> list;

    PollTask(ConcurrentLinkedDeque<String> list) {
      this.list = list;
    }

    @Override
    public void run() {
      for (int i = 0; i < 5000; i++) {
        list.pollFirst();
        list.pollLast();
      }
    }
  }
}
