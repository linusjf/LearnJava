package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ProducerConsumerBlockingQueue {
  ;

  public static void main(String[] args) {
    try {
      BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>(2);

      Thread producerThread = new Thread(() -> {
        try {
          int value = 0;
          while (true) {
            blockingQueue.put(value);
            System.out.println("Produced " + value);
            value++;
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      });

      Thread consumerThread = new Thread(() -> {
        try {
          while (true) {
            int value = blockingQueue.take();
            System.out.println("Consumed " + value);
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      });

      Thread terminatorThread = new Thread(() -> {
        try {
          Thread.sleep(10_000);
          System.err.println("Exiting program...");
          System.exit(0);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      });

      producerThread.start();
      consumerThread.start();
      terminatorThread.start();
      producerThread.join();
      consumerThread.join();
      terminatorThread.join();
    } catch (InterruptedException ex) {
      System.err.println(ex);
    }
  }
}
