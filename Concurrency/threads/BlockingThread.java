package threads;

public class BlockingThread extends Thread {
  private SimpleBlockingQueue queue;
  private boolean wasInterrupted = false;
  private boolean reachedAfterGet = false;
  private boolean throwableThrown;

  public BlockingThread(SimpleBlockingQueue queue) {
    this.queue = queue;
  }

  public void run() {
    try {
      try {
        queue.get();
      } catch (InterruptedException e) {
        wasInterrupted = true;
      }
      reachedAfterGet = true;
    } catch (Throwable t) {
      throwableThrown = true;
    }
  }

  public boolean isWasInterrupted() {
    return wasInterrupted;
  }

  public boolean isReachedAfterGet() {
    return reachedAfterGet;
  }

  public boolean isThrowableThrown() {
    return throwableThrown;
  }

  public static void main(String... args) {
    try {
      testPutOnEmptyQueueBlocks();
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }

  static void testPutOnEmptyQueueBlocks() throws InterruptedException {
    final SimpleBlockingQueue queue = new SimpleBlockingQueue();
    BlockingThread blockingThread = new BlockingThread(queue);
    blockingThread.start();
    Thread.sleep(5000);
    assert blockingThread.isReachedAfterGet() == false;
    assert blockingThread.isWasInterrupted() == false;
    assert blockingThread.isThrowableThrown() == false;
    queue.put(new Object());
    Thread.sleep(1000);
    assert blockingThread.isReachedAfterGet() == true;
    assert blockingThread.isWasInterrupted() == false;
    assert blockingThread.isThrowableThrown() == false;
    blockingThread.join();
  }
}
