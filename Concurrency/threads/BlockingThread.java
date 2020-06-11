package threads;

import java.util.concurrent.TimeUnit;

public class BlockingThread extends Thread {
  private final SimpleBlockingQueue<?> queue;
  private boolean wasInterrupted;
  private boolean reachedAfterGet;
  private boolean throwableThrown;

  public BlockingThread(SimpleBlockingQueue<?> queue) {
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
    TimeUnit.MILLISECONDS.sleep(5000);
    assert !blockingThread.isReachedAfterGet();
    assert !blockingThread.isWasInterrupted();
    assert !blockingThread.isThrowableThrown();
    queue.put(new Object());
    TimeUnit.MILLISECONDS.sleep(1000);
    assert blockingThread.isReachedAfterGet();
    assert !blockingThread.isWasInterrupted();
    assert !blockingThread.isThrowableThrown();
    blockingThread.join(10000);
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "BlockingThread(queue=" + this.queue + ", wasInterrupted=" + this.isWasInterrupted() + ", reachedAfterGet=" + this.isReachedAfterGet() + ", throwableThrown=" + this.isThrowableThrown() + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof BlockingThread)) return false;
    BlockingThread other = (BlockingThread) o;
    if (!other.canEqual((Object) this)) return false;
    if (!super.equals(o)) return false;
    Object this$queue = this.queue;
    Object other$queue = other.queue;
    if (this$queue == null ? other$queue != null : !this$queue.equals(other$queue)) return false;
    if (this.isWasInterrupted() != other.isWasInterrupted()) return false;
    if (this.isReachedAfterGet() != other.isReachedAfterGet()) return false;
    if (this.isThrowableThrown() != other.isThrowableThrown()) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof BlockingThread;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = super.hashCode();
    Object $queue = this.queue;
    result = result * PRIME + ($queue == null ? 43 : $queue.hashCode());
    result = result * PRIME + (this.isWasInterrupted() ? 79 : 97);
    result = result * PRIME + (this.isReachedAfterGet() ? 79 : 97);
    result = result * PRIME + (this.isThrowableThrown() ? 79 : 97);
    return result;
  }
}
