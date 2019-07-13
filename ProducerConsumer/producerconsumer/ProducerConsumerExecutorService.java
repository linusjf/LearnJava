package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public enum ProducerConsumerExecutorService {
  ;

  public static void main(String[] args) {
    BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>(2);
    final ExecutorService executor = Executors.newFixedThreadPool(3);

    Runnable producerTask = () -> {
      try {
        int value = 0;
        while (true) {
          blockingQueue.put(value);
          System.out.println("Produced " + value);
          value++;
          Thread.sleep(1000);
        }
      } catch (InterruptedException e) {
        System.err.println("Producer thread: " + e.getMessage());
      }
    };

    Runnable consumerTask = () -> {
      try {
        while (true) {
          int value = blockingQueue.take();
          System.out.println("Consumed " + value);
          Thread.sleep(1000);
        }
      } catch (InterruptedException e) {
        System.err.println("Consumer thread: " + e.getMessage());
      }
    };

    Runnable terminatorTask = () -> {
      try {
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Closing executor service...");
        executor.shutdownNow();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    };
    executor.execute(producerTask);
    executor.execute(consumerTask);
    executor.execute(terminatorTask);
  }
}
