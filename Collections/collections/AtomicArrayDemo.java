package collections;

import java.util.concurrent.atomic.AtomicIntegerArray;

public enum AtomicArrayDemo {
  ;
  private static final int THREADS = 100;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    AtomicIntegerArray vector = new AtomicIntegerArray(1000);
    Incrementer incrementer = new Incrementer(vector);
    Decrementer decrementer = new Decrementer(vector);
    Thread[] threadIncrementer = new Thread[THREADS];
    Thread[] threadDecrementer = new Thread[THREADS];
    for (int i = 0; i < THREADS; i++) {
      threadIncrementer[i] = new Thread(incrementer);
      threadDecrementer[i] = new Thread(decrementer);
      threadIncrementer[i].start();
      threadDecrementer[i].start();
    }
    for (int i = 0; i < 100; i++) {
      try {
        threadIncrementer[i].join();
        threadDecrementer[i].join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    int counter = 0;
    for (int i = 0; i < vector.length(); i++) {
      if (vector.get(i) != 0) {
        System.out.println("Vector[" + i + "] : " + vector.get(i));
        counter++;
      }
    }
    System.out.printf("No. of non-zero elements: %d%n", counter);
  }

  @SuppressWarnings("PMD.BeanFieldsShouldSerialize")
  static class Incrementer implements Runnable {
    private final AtomicIntegerArray vector;

    Incrementer(AtomicIntegerArray vector) {
      this.vector = vector;
    }

    @Override
    public void run() {
      for (int i = 0; i < vector.length(); i++)
        vector.getAndIncrement(i);
    }
  }

  @SuppressWarnings("PMD.BeanFieldsShouldSerialize")
  static class Decrementer implements Runnable {
    private final AtomicIntegerArray vector;

    Decrementer(AtomicIntegerArray vector) {
      this.vector = vector;
    }

    @Override
    public void run() {
      for (int i = 0; i < vector.length(); i++)
        vector.getAndDecrement(i);
    }
  }
}
