package threads;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("PMD.SystemPrintln")
public class RunnableHelloCount implements Runnable {
  Thread thread1 = new Thread(this);
  Thread thread2 = new Thread(this);
  private final Random random = new Random();

  public void start() {
    thread1.start();
    thread2.start();
  }

  public static void main(String[] args) {
    new RunnableHelloCount().start();
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void run() {
    int pause;
    for (int i = 0; i < 10; i++) {
      try {
        System.out.println(Thread.currentThread().getName()
                           + " being executed.");
        pause = random.nextInt(3000);
        TimeUnit.MILLISECONDS.sleep(pause);
      } catch (InterruptedException interruptEx) {
        System.out.println(interruptEx);
        Thread.currentThread().interrupt();
      }
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof RunnableHelloCount))
      return false;
    RunnableHelloCount other = (RunnableHelloCount)o;
    if (!other.canEqual((Object)this))
      return false;
    Object this$thread1 = this.thread1;
    Object other$thread1 = other.thread1;
    if (this$thread1 == null ? other$thread1 != null
                             : !this$thread1.equals(other$thread1))
      return false;
    Object this$thread2 = this.thread2;
    Object other$thread2 = other.thread2;
    if (this$thread2 == null ? other$thread2 != null
                             : !this$thread2.equals(other$thread2))
      return false;
    Object this$random = this.random;
    Object other$random = other.random;
    if (this$random == null ? other$random != null
                            : !this$random.equals(other$random))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof RunnableHelloCount;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $thread1 = this.thread1;
    result = result * PRIME + ($thread1 == null ? 43 : $thread1.hashCode());
    Object $thread2 = this.thread2;
    result = result * PRIME + ($thread2 == null ? 43 : $thread2.hashCode());
    Object $random = this.random;
    result = result * PRIME + ($random == null ? 43 : $random.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "RunnableHelloCount(thread1=" + this.thread1
        + ", thread2=" + this.thread2 + ", random=" + this.random + ")";
  }
}
