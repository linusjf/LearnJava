package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ProducerConsumerBlockingQueue {
  ;

  @SuppressWarnings("PMD.AvoidThreadGroup")
  public static void main(String[] args) {
    try {
      BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>(2);

      ThreadGroup group = new ThreadGroup("PCBQ");

      Thread producerThread = new Thread(group, () -> {
        try {
          int value = 0;
          while (!Thread.interrupted()) {
            blockingQueue.put(value);
            System.out.println("Produced " + value);
            value++;
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          System.err.println("Producer thread: " + e.getMessage());
          return;
        }
      });

      Thread consumerThread = new Thread(group, () -> {
        try {
          while (!Thread.interrupted()) {
            int value = blockingQueue.take();
            System.out.println("Consumed " + value);
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          System.err.println("Consumer thread: " + e.getMessage());
          return;
        }
      });

      Thread terminatorThread = new Thread(() -> {
        try {
          Thread.sleep(10_000);
          System.err.println("Exiting program...");
          synchronized(group) {
          group.interrupt();
          }
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
