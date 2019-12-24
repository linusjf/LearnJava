package threads;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public enum LockProducerConsumer {
  ;

  public static void main(String[] args) {
    FileMock mock = new FileMock(100, 10);
    Buffer buffer = new Buffer(20);
    Producer producer = new Producer(mock, buffer);
    Thread threadProducer = new Thread(producer, "Producer");
    Thread[] threadConsumers = new Thread[3];
    Arrays.setAll(threadConsumers, i -> new Thread(new Consumer(buffer), "Consumer " + i));
    threadProducer.start();
    for (Thread t : threadConsumers) t.start();
  }

  static class FileMock {
    private final String[] content;
    private int index;
    private final Random random = new Random();

    FileMock(int size, int length) {
      content = new String[size];
      for (int i = 0; i < size; i++) {
        StringBuilder buffer = new StringBuilder(length);
        for (int j = 0; j < length; j++) {
          int indice = random.nextInt(255);
          buffer.append((char) indice);
        }
        content[i] = buffer.toString();
      }
      index = 0;
    }

    public boolean hasMoreLines() {
      return index < content.length;
    }

    public String getLine() {
      if (this.hasMoreLines()) {
        System.out.println("Mock: " + (content.length - index));
        return content[index++];
      }
      return null;
    }
  }

  static class Buffer {
    private final Deque<String> queue;

    private final int maxSize;

    private final ReentrantLock lock;

    private final Condition lines;
    private final Condition space;
    private boolean pendingLines;

    Buffer(int maxSize) {
      this.maxSize = maxSize;
      queue = new LinkedList<>();
      lock = new ReentrantLock();
      lines = lock.newCondition();
      space = lock.newCondition();
      pendingLines = true;
    }

    public void insert(String line) {
      lock.lock();
      try {
        while (queue.size() == maxSize) space.await();
        queue.offer(line);
        System.out.printf(
            "%s: Inserted Line: %d%n", Thread.currentThread().getName(), queue.size());
        lines.signalAll();
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        lock.unlock();
      }
    }

    public String get() {
      lock.lock();
      try {
        while (queue.size() == 0 && hasPendingLines()) lines.await();

        if (hasPendingLines()) {
          String line = queue.poll();
          System.out.printf("%s: Line Read: %d%n", Thread.currentThread().getName(), queue.size());
          space.signalAll();
          return line;
        }
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        lock.unlock();
      }
      return null;
    }

    public void setPendingLines(boolean pendingLines) {
      this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines() {
      return pendingLines || queue.size() > 0;
    }
  }

  static class Producer implements Runnable {
    private final FileMock mock;

    private final Buffer buffer;

    Producer(FileMock mock, Buffer buffer) {
      this.mock = mock;
      this.buffer = buffer;
    }

    @Override
    public void run() {
      buffer.setPendingLines(true);
      while (mock.hasMoreLines()) {
        String line = mock.getLine();
        buffer.insert(line);
      }
      buffer.setPendingLines(false);
    }
  }

  static class Consumer implements Runnable {
    private final Buffer buffer;

    Consumer(Buffer buffer) {
      this.buffer = buffer;
    }

    @Override
    public void run() {
      while (buffer.hasPendingLines()) {
        String line = buffer.get();
        processLine(line);
      }
    }

    private void processLine(String line) {
      try {
        System.out.printf("About to process line: %s", line);
        Random random = new Random();
        Thread.sleep(random.nextInt(100));
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
  }
}
