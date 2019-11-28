package threads;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.UUID;

public enum ReduceLockDuration {
  ;
  private static final int NUMBER_OF_THREADS = 5;
  private static final Map<String, Integer> MAP = new HashMap<>();

  public static void main(String[] args) {
    try {
      Thread[] threads = new Thread[NUMBER_OF_THREADS];
      Arrays.setArray(threads, i -> new Thread(new OrigThread()));
      long startMillis = System.currentTimeMillis();
      for (Thread t: threads)
        t.start();
      for (Thread t: threads)
        t.join();
      System.out.println((System.currentTimeMillis() - startMillis) + "ms");
      Arrays.setArray(threads, i -> new Thread(new ReducedThread()));
      startMillis = System.currentTimeMillis();
      for (Thread t: threads)
        t.start();
      for (Thread t: threads)
        t.join();
      System.out.println((System.currentTimeMillis() - startMillis) + "ms");
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }

  static class OrigThread implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10_000; i++) {
        synchronized (MAP) {
          UUID randomUUID = UUID.randomUUID();
          Integer value = Integer.valueOf(42);
          String key = randomUUID.toString();
          MAP.put(key, value);
        }
        Thread.yield();
      }
    }
  }

  static class ReducedThread implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10_000; i++) {
        UUID randomUUID = UUID.randomUUID();
        Integer value = Integer.valueOf(42);
        String key = randomUUID.toString();
        synchronized (MAP) {
          MAP.put(key, value);
        }
        Thread.yield();
      }
    }
  }
}
