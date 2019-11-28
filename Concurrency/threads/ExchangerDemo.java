package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public enum ExchangerDemo {
  ;

  public static void main(String[] args) {
    List<String> buffer1 = new ArrayList<>();
    List<String> buffer2 = new ArrayList<>();
    Exchanger<List<String>> exchanger = new Exchanger<>();
    Producer producer = new Producer(buffer1, exchanger);
    Consumer consumer = new Consumer(buffer2, exchanger);
    Thread threadProducer = new Thread(producer);
    Thread threadConsumer = new Thread(consumer);

    threadProducer.start();
    threadConsumer.start();
  }

  static class Producer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
      this.buffer = buffer;
      this.exchanger = exchanger;
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    @Override
    public void run() {
      int cycle = 1;

      for (int i = 0; i < 10; i++) {
        System.out.printf("Producer: Cycle %d\n", cycle++);
        for (int j = 0; j < 10; j++) {
          String message = "Event " + (i * 10 + j);
          System.out.printf("Producer: %s\n", message);
          buffer.add(message);
        }
        try {
          buffer = exchanger.exchange(buffer);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
        System.out.println("Producer buffer size: " + buffer.size());
      }
    }
  }

  static class Consumer implements Runnable {
    private List<String> buffer;

    private final Exchanger<List<String>> exchanger;

    Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
      this.buffer = buffer;
      this.exchanger = exchanger;
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    @Override
    public void run() {
      int cycle = 1;

      for (int i = 0; i < 10; i++) {
        System.out.printf("Consumer: Cycle %d\n", cycle++);
        try {
          buffer = exchanger.exchange(buffer);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
        System.out.println("Consumer buffer size: " + buffer.size());

        for (int j = 0; j < 10; j++) {
          String message = buffer.get(0);
          System.out.println("Consumer: " + message);
          buffer.remove(0);
        }
      }
    }
  }
}
