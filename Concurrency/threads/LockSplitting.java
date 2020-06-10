package threads;

import java.util.concurrent.ThreadLocalRandom;

public class LockSplitting implements Runnable {
  private static final int NUMBER_OF_THREADS = 5;
  private final Counter counter;

  public LockSplitting(Counter counter) {
    this.counter = counter;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void run() {
    for (int i = 0; i < 100_000; i++) {
      if (ThreadLocalRandom.current().nextBoolean()) 
        counter.incrementCustomer();
       else 
         counter.incrementShipping();
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    try {
      Thread[] threads = new Thread[NUMBER_OF_THREADS];
      for (int i = 0; i < NUMBER_OF_THREADS; i++) 
        threads[i] = new Thread(new LockSplitting(new CounterOneLock()));
      long startMillis = System.currentTimeMillis();
      for (Thread t : threads) 
        t.start();
      for (Thread t : threads) 
        t.join(100_000);
      System.out.println((System.currentTimeMillis() - startMillis) + "ms");
      for (int i = 0; i < NUMBER_OF_THREADS; i++) threads[i] = new Thread(new LockSplitting(new CounterSeparateLock()));
      startMillis = System.currentTimeMillis();
      for (Thread t : threads) 
        t.start();
      for (Thread t : threads) 
        t.join(100_000);
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
    private Object lock = new Object();

    @Override
    public void incrementCustomer() {
      synchronized (lock) {
        customerCount++;
      }
    }

    @Override
    public void incrementShipping() {
      synchronized (lock) {
        shippingCount++;
      }
    }

    @Override
    public long getCustomerCount() {
      synchronized (lock) {
        return customerCount;
      }
    }

    @Override
    public long getShippingCount() {
      synchronized (lock) {
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

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof LockSplitting)) return false;
    LockSplitting other = (LockSplitting) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$counter = this.counter;
    Object other$counter = other.counter;
    if (this$counter == null ? other$counter != null : !this$counter.equals(other$counter)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof LockSplitting;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $counter = this.counter;
    result = result * PRIME + ($counter == null ? 43 : $counter.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "LockSplitting(counter=" + this.counter + ")";
  }
}
