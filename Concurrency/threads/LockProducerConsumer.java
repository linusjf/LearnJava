package threads;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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
    Arrays.setAll(threadConsumers,
                  i -> new Thread(new Consumer(buffer), "Consumer " + i));
    threadProducer.start();
    for (Thread t: threadConsumers)
      t.start();
  }

  @SuppressWarnings("all")
  static void ignore(Object o) {
    // no-op method
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
          buffer.append((char)indice);
        }
        content[i] = buffer.toString();
      }
      index = 0;
    }

    public boolean hasMoreLines() {
      return index < content.length;
    }

    public String getLine() {
      if (!hasMoreLines()) 
        return null;
      System.out.println("Mock: " + (content.length - index));
      return content[index++];
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

    @SuppressWarnings("PMD.LawOfDemeter")
    public void insert(String line) throws InterruptedException, TimeoutException {
      if (lock.tryLock(1, TimeUnit.SECONDS)) {
      try {
        while (queue.size() == maxSize)
          ignore(space.await(1, TimeUnit.MILLISECONDS));
        queue.offer(line);
        System.out.printf("%s: Inserted Line: %d%n",
                          Thread.currentThread().getName(),
                          queue.size());
        lines.signalAll();
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        lock.unlock();
      }
      }
      else 
        throw new TimeoutException("Timed out after 1 second: '"
            + line + "' cannot be inserted");
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    public String get() throws InterruptedException, TimeoutException {
      String line = null;
    if (lock.tryLock(1, TimeUnit.SECONDS)) {
        try {
          while (queue.size() == 0 && hasPendingLines())
          ignore(lines.await(1,TimeUnit.MILLISECONDS));

        if (hasPendingLines()) {
          line = queue.poll();
          System.out.printf("%s: Line Read: %d%n",
                            Thread.currentThread().getName(),
                            queue.size());
          space.signalAll();
        }
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        lock.unlock();
      }
    return line;
    }
    else throw new TimeoutException("Error in get in class "
        + getClass());
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
      try {
      buffer.setPendingLines(true);
      while (mock.hasMoreLines()) {
        String line = mock.getLine();
        buffer.insert(line);
      }
      buffer.setPendingLines(false);
      } catch (InterruptedException ie) {
        Thread.currentThread().interrupt();
      } catch (TimeoutException te) {
        System.err.println(te.getMessage());
      }
    }
  }

  static class Consumer implements Runnable {
    private final Buffer buffer;

    Consumer(Buffer buffer) {
      this.buffer = buffer;
    }

    @Override
    public void run() {
    try {
      while (buffer.hasPendingLines()) {
        String line = buffer.get();
        processLine(line);
      }
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
    } catch (TimeoutException te) {
      System.err.println(te.getMessage());
    }
    }

    private void processLine(String line) {
      try {
        System.out.printf("About to process line: %s", line);
        Random random = new Random();
        TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
  }
}
