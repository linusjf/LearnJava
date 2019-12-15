package threads;

import java.util.concurrent.ThreadLocalRandom;

public class LockSplitting implements Runnable {
  private static final int NUMBER_OF_THREADS = 5;
  private final Counter counter;

  public LockSplitting(Counter counter) {
    this.counter = counter;
  }

  @Override
  public void run() {
    for (int i = 0; i < 100_000; i++) {
      if (
        ThreadLocalRandom.current().nextBoolean()
      ) counter.incrementCustomer(); else counter.incrementShipping();
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    try {
      Thread[] threads = new Thread[NUMBER_OF_THREADS];
      for (int i = 0; i < NUMBER_OF_THREADS; i++) threads[i] =
        new Thread(new LockSplitting(new CounterOneLock()));

      long startMillis = System.currentTimeMillis();
      for (Thread t : threads) t.start();

      for (Thread t : threads) t.join();

      System.out.println((System.currentTimeMillis() - startMillis) + "ms");
      for (int i = 0; i < NUMBER_OF_THREADS; i++) threads[i] =
        new Thread(new LockSplitting(new CounterSeparateLock()));

      startMillis = System.currentTimeMillis();
      for (Thread t : threads) t.start();

      for (Thread t : threads) t.join();

      System.out.println((System.currentTimeMillis() - startMillis) + "ms");
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }

  public interface Counter {
    void incrementCustomer();

    void incrementShipping();

    long getCustomerCount();

    long getShippingCount();
  }

  public static class CounterOneLock implements Counter {
    private long customerCount;
    private long shippingCount;

    @Override
    public void incrementCustomer() {
      synchronized (this) {
        customerCount++;
      }
    }

    @Override
    public void incrementShipping() {
      synchronized (this) {
        shippingCount++;
      }
    }

    @Override
    public long getCustomerCount() {
      synchronized (this) {
        return customerCount;
      }
    }

    @Override
    public long getShippingCount() {
      synchronized (this) {
        return shippingCount;
      }
    }
  }

  public static class CounterSeparateLock implements Counter {
    private static final Object CUSTOMER_LOCK = new Object();
    private static final Object SHIPPING_LOCK = new Object();
    private long customerCount;
    private long shippingCount;

    @Override
    public void incrementCustomer() {
      synchronized (CUSTOMER_LOCK) {
        customerCount++;
      }
    }

    @Override
    public void incrementShipping() {
      synchronized (SHIPPING_LOCK) {
        shippingCount++;
      }
    }

    @Override
    public long getCustomerCount() {
      synchronized (CUSTOMER_LOCK) {
        return customerCount;
      }
    }

    @Override
    public long getShippingCount() {
      synchronized (SHIPPING_LOCK) {
        return shippingCount;
      }
    }
  }
}
