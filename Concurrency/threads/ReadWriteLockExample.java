package threads;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public enum ReadWriteLockExample {
  ;

  public static void main(String[] args) {
    PricesInfo pricesInfo = new PricesInfo();
    Thread[] threadsReader = new Thread[5];
    Arrays.setAll(threadsReader, i -> new Thread(new Reader(pricesInfo)));
    Writer writer = new Writer(pricesInfo);
    Writer writer2 = new Writer(pricesInfo);
    Thread threadWriter = new Thread(writer);
    Thread threadWriter2 = new Thread(writer2);
    for (Thread t: threadsReader)
      t.start();
    threadWriter.start();
    threadWriter2.start();
  }

  static class PricesInfo {
    private double price1;
    private double price2;
    private final ReadWriteLock lock;
    private final Random random = new Random();

    PricesInfo() {
      price1 = 1.0;
      price2 = 2.0;
      boolean fair = random.nextBoolean();
      lock = new ReentrantReadWriteLock(fair);
      System.out.println("Fair mode: " + fair);
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    public double getPrice1() throws InterruptedException, TimeoutException {
      if (lock.readLock().tryLock(1, TimeUnit.SECONDS)) {
        double value = price1;
        lock.readLock().unlock();
        return value;
      }
      throw new TimeoutException("Unable to read price1 in class "
                                 + getClass());
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    public double getPrice2() throws InterruptedException, TimeoutException {
      if (lock.readLock().tryLock(1, TimeUnit.SECONDS)) {
        double value = price2;
        lock.readLock().unlock();
        return value;
      }
      throw new TimeoutException("Unable to read price2 in class "
                                 + getClass());
    }

    @SuppressWarnings({"checkstyle:hiddenfield", "PMD.LawOfDemeter"})
    public void setPrices(double price1, double price2)
        throws InterruptedException, TimeoutException {
      if (lock.writeLock().tryLock(1, TimeUnit.SECONDS)) {
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
      }
      throw new TimeoutException("Unable to set prices in class " + getClass());
    }
  }

  static class Reader implements Runnable {
    private final PricesInfo pricesInfo;

    Reader(PricesInfo pricesInfo) {
      this.pricesInfo = pricesInfo;
    }

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void run() {
      try {
        for (int i = 0; i < 10; i++) {
          synchronized (System.out) {
            System.out.printf("%s: Price 1: %f%n",
                              Thread.currentThread().getName(),
                              pricesInfo.getPrice1());
            System.out.printf("%s: Price 2: %f%n",
                              Thread.currentThread().getName(),
                              pricesInfo.getPrice2());
          }
        }
      } catch (InterruptedException ie) {
        Thread.currentThread().interrupt();
      } catch (TimeoutException te) {
        System.err.println(te.getMessage());
      }
    }
  }

  static class Writer implements Runnable {
    private final PricesInfo pricesInfo;

    Writer(PricesInfo pricesInfo) {
      this.pricesInfo = pricesInfo;
    }

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void run() {
      try {
        for (int i = 0; i < 3; i++) {
          synchronized (System.out) {
            System.out.printf("Writer %s: Attempt to modify the prices.%n",
                              Thread.currentThread().getName());
          }
          pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
          synchronized (System.out) {
            System.out.printf("Writer %s: Prices have been modified.%n",
                              Thread.currentThread().getName());
          }
          TimeUnit.MILLISECONDS.sleep(2);
        }
      } catch (InterruptedException ie) {
        Thread.currentThread().interrupt();
      } catch (TimeoutException te) {
        System.err.println(te.getMessage());
      }
    }
  }
}
