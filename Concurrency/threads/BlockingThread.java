package threads;

public class BlockingThread extends Thread {
  private final SimpleBlockingQueue<?> queue;
  private boolean wasInterrupted;
  private boolean reachedAfterGet;
  private boolean throwableThrown;

  public BlockingThread(SimpleBlockingQueue<?> queue) {
    super();
    this.queue = queue;
  }

  @SuppressWarnings("PMD.AvoidCatchingThrowable")
  @Override
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
    final SimpleBlockingQueue<Object> queue = new SimpleBlockingQueue<>();
    BlockingThread blockingThread = new BlockingThread(queue);
    blockingThread.start();
    Thread.sleep(5000);
    assert !blockingThread.isReachedAfterGet();
    assert !blockingThread.isWasInterrupted();
    assert !blockingThread.isThrowableThrown();
    queue.put(new Object());
    Thread.sleep(1000);
    assert blockingThread.isReachedAfterGet();
    assert !blockingThread.isWasInterrupted();
    assert !blockingThread.isThrowableThrown();
    blockingThread.join();
  }
}
