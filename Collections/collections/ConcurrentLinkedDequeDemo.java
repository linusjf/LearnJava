package collections;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

public enum ConcurrentLinkedDequeDemo {
  ;

  public static void main(String[] args) {
    ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
    Thread[] threads = new Thread[100];
    for (int i = 0; i < threads.length; i++) {
      AddTask task = new AddTask(list);
      threads[i] = new Thread(task);
      threads[i].start();
    }
    System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("Main: Size of the List: %d\n", list.size());
    for (int i = 0; i < threads.length; i++) {
      PollTask task = new PollTask(list);
      threads[i] = new Thread(task);
      threads[i].start();
    }
    System.out.printf("Main: %d PollTask threads have been launched\n", threads.length);
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("Main: Size of the List: %d\n", list.size());
    alternateMain();
  }

  private static void alternateMain() {

    Random random = new Random();
    ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
    Thread[] threads = new Thread[100];
    Thread[] threads2 = new Thread[100];
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
    System.out.printf("Alternate Main: %d AddTask threads have been launched\n", threads.length);
    System.out.printf(
        "Alternate Main: %d PollTask threads have been launched simultaneously\n", threads2.length);
    System.out.printf("Alternate Main: Size of the List: %d\n", list.size());

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
    System.out.printf("Alternate Main: Size of the List: %d\n", list.size());
  }

  static class AddTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

    AddTask(ConcurrentLinkedDeque<String> list) {
      this.list = list;
    }

    @Override
    public void run() {
      String name = Thread.currentThread().getName();
      for (int i = 0; i < 10_000; i++) {
        list.add(name + ": Element " + i);
      }
    }
  }

  static class PollTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

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
