package unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public enum TestCounters {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(TestCounters.class.getName());

      private static int NUM_OF_THREADS = 1000;

      private static long NUM_OF_INCREMENTS = 10_000;

  public static void main(String... args) {
    try {
      ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
      Counter counter = new SynchronizedCounter();
      // creating instance of specific counter
      long before = System.currentTimeMillis();
      for (int i = 0; i < NUM_OF_THREADS; i++) {
        service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
      }
      service.shutdown();
      service.awaitTermination(1, TimeUnit.MINUTES);
      long after = System.currentTimeMillis();
      System.out.println("Counter result: " + counter.get());
      System.out.println("Time passed in ms:" + (after - before));
      service = Executors.newFixedThreadPool(NUM_OF_THREADS);
      counter = new AtomicCounter();
      // creating instance of specific counter
      before = System.currentTimeMillis();
      for (int i = 0; i < NUM_OF_THREADS; i++) {
        service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
      }
      service.shutdown();
      service.awaitTermination(1, TimeUnit.MINUTES);
      after = System.currentTimeMillis();
      System.out.println("Counter result: " + counter.get());
      System.out.println("Time passed in ms:" + (after - before));
      service = Executors.newFixedThreadPool(NUM_OF_THREADS);
      counter = new LockCounter();
      // creating instance of specific counter
      before = System.currentTimeMillis();
      for (int i = 0; i < NUM_OF_THREADS; i++) {
        service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
      }
      service.shutdown();
      service.awaitTermination(1, TimeUnit.MINUTES);
      after = System.currentTimeMillis();
      System.out.println("Counter result: " + counter.get());
      System.out.println("Time passed in ms:" + (after - before));
    } catch (InterruptedException ie) {
      System.err.println(ie.getMessage());
    }
  }
}

