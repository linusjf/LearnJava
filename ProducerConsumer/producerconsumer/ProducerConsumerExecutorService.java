package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public enum ProducerConsumerExecutorService {
  ;
  static final BlockingQueue<Integer> BLOCKING_QUEUE = new LinkedBlockingDeque<>(2);
  static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(3);

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    Runnable producerTask =
        () -> {
          try {
            int value = 0;
            while (!EXECUTOR.isShutdown()) {
              BLOCKING_QUEUE.put(value);
              System.out.println("Produced " + value);
              value++;
              Thread.sleep(1000);
            }
          } catch (InterruptedException e) {
            System.err.println("Producer thread: " + e.getMessage());
          }
        };

    Runnable consumerTask =
        () -> {
          try {
            while (!EXECUTOR.isShutdown()) {
              int value = BLOCKING_QUEUE.take();
              System.out.println("Consumed " + value);
              Thread.sleep(1000);
            }
          } catch (InterruptedException e) {
            System.err.println("Consumer thread: " + e.getMessage());
          }
        };

    Runnable terminatorTask =
        () -> {
          try {
            Thread.sleep(10_000);
            EXECUTOR.shutdown();
            System.out.println("Blocking for 10 seconds...");
            EXECUTOR.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("Closing executor service...");
            //  EXECUTOR.shutdownNow();
          } catch (InterruptedException e) {
            System.err.println(e);
          }
        };
    EXECUTOR.execute(producerTask);
    EXECUTOR.execute(consumerTask);
    EXECUTOR.execute(terminatorTask);
  }
}
