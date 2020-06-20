package threads;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public enum Loaders {
  ;

  public static void main(String[] args) {
    DataSourcesLoader dsLoader = new DataSourcesLoader();
    Thread thread1 = new Thread(dsLoader, "DataSourceThread");
    NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
    Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");
    thread1.start();
    thread2.start();

    try {
      thread1.join(10_000);
      thread2.join(10_000);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    System.out.printf("Main: Configuration has been loaded: %s%n", new Date());
  }

  static class DataSourcesLoader implements Runnable {
    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void run() {
      System.out.printf("Beginning data sources loading: %s%n", new Date());
      try {
        TimeUnit.SECONDS.sleep(4);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
      System.out.printf("Data sources loading has finished: %s%n", new Date());
    }
  }

  static class NetworkConnectionsLoader implements Runnable {
    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void run() {
      System.out.printf("Beginning network connections loading: %s%n", new Date());
      try {
        TimeUnit.SECONDS.sleep(6);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
      System.out.printf("Network connections loading has finished: %s%n", new Date());
    }
  }
}
