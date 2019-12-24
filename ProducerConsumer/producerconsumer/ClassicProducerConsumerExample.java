package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public enum ClassicProducerConsumerExample {
  ;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) throws InterruptedException {
    final Buffer buffer = new Buffer(2);

    Thread producerThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          buffer.produce();
        } catch (InterruptedException e) {
          System.err.println(e);
          Thread.currentThread().interrupt();
        }
      }
    });

    Thread consumerThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          buffer.consume();
        } catch (InterruptedException e) {
          System.err.println(e);
          Thread.currentThread().interrupt();
        }
      }
    });

    Thread terminatorThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(10_000);
          System.out.println("Exiting program...");
          producerThread.interrupt();
          consumerThread.interrupt();
          // System.exit(0);
        } catch (InterruptedException e) {
          System.err.println(e);
          Runtime.getRuntime().halt(0);
        }
      }
    });
    producerThread.start();
    consumerThread.start();
    terminatorThread.start();
    producerThread.join();
    consumerThread.join();
    terminatorThread.join();
  }

  static class Buffer {
    private final Queue<Integer> list;
    private final int size;
    private final Random random = new Random();

    Buffer(int size) {
      this.list = new LinkedList<>();
      this.size = size;
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void produce() throws InterruptedException {
      int value = 0;

      // equivalent of while(true)
      while (value >= 0) {
        Thread.sleep(random.nextInt(1000));
        synchronized (this) {
          while (list.size() >= size) {
            // wait for the consumer
            wait();
          }
          list.add(value);
          System.out.println("Produced " + value);
          value++;

          // notify the consumer
          notifyAll();
        }
      }
    }

    public void consume() throws InterruptedException {
      while (true) {
        Thread.sleep(random.nextInt(1000));
        synchronized (this) {
          while (list.size() == 0) {
            // wait for the producer
            wait();
          }
          int value = list.poll();
          System.out.println("Consumed " + value);

          // notify the producer
          notifyAll();
        }
      }
    }
  }
}
