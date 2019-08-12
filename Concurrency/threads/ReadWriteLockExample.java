package threads;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public enum ReadWriteLockExample {
  ;

  public static void main(String[] args) {
    PricesInfo pricesInfo = new PricesInfo();
    Reader[] readers = new Reader[5];
    Thread[] threadsReader = new Thread[5];

    for (int i = 0; i < 5; i++) {
      readers[i] = new Reader(pricesInfo);
      threadsReader[i] = new Thread(readers[i]);
    }
    Writer writer = new Writer(pricesInfo);
    Writer writer2 = new Writer(pricesInfo);
    Thread threadWriter = new Thread(writer);
    Thread threadWriter2 = new Thread(writer2);
    for (int i = 0; i < 5; i++) {
      threadsReader[i].start();
    }
    threadWriter.start();
    threadWriter2.start();
  }

  static class PricesInfo {
    private double price1;
    private double price2;
    private ReadWriteLock lock;
    private Random random = new Random();

    PricesInfo() {
      price1 = 1.0;
      price2 = 2.0;
      boolean fair = random.nextBoolean();
      lock = new ReentrantReadWriteLock(fair);
      System.out.println("Fair mode: " + fair);
    }

    public double getPrice1() {
      lock.readLock().lock();
      double value = price1;
      lock.readLock().unlock();
      return value;
    }

    public double getPrice2() {
      lock.readLock().lock();
      double value = price2;
      lock.readLock().unlock();
      return value;
    }

    @SuppressWarnings("checkstyle:hiddenfield")
    public void setPrices(double price1, double price2) {
      lock.writeLock().lock();
      this.price1 = price1;
      this.price2 = price2;
      lock.writeLock().unlock();
    }
  }

  static class Reader implements Runnable {
    private PricesInfo pricesInfo;

    Reader(PricesInfo pricesInfo) {
      this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {

        synchronized (System.out) {
          System.out.printf("%s: Price 1: %f\n",
                            Thread.currentThread().getName(),
                            pricesInfo.getPrice1());
          System.out.printf("%s: Price 2: %f\n",
                            Thread.currentThread().getName(),
                            pricesInfo.getPrice2());
        }
      }
    }
  }

  static class Writer implements Runnable {
    private PricesInfo pricesInfo;

    Writer(PricesInfo pricesInfo) {
      this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
      for (int i = 0; i < 3; i++) {
        synchronized (System.out) {
          System.out.printf("Writer %s: Attempt to modify the prices.\n",
                            Thread.currentThread().getName());
        }
        pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
        synchronized (System.out) {
          System.out.printf("Writer %s: Prices have been modified.\n",
                            Thread.currentThread().getName());
        }
        try {
          Thread.sleep(2);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      }
    }
  }
}
