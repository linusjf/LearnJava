package threads;

import java.util.Random;

public class ThreadShowName extends Thread {
  private final Random random = new Random();

  public static void main(String[] args) {
    ThreadShowName thread1 = new ThreadShowName();
    ThreadShowName thread2 = new ThreadShowName();
    thread1.start();
    // Will call run.
    thread2.start();
    // Will call run.
  }

  @Override
  public void run() {
    int pause;
    for (int i = 0; i < 10; i++) {
      try {
        System.out.println(getName() + " being executed.");
        pause = random.nextInt(3000);
        sleep(pause);
      } catch (
      // 0-3 seconds.
      InterruptedException interruptEx) {
        System.out.println(interruptEx);
        interrupt();
      }
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof ThreadShowName)) return false;
    ThreadShowName other = (ThreadShowName) o;
    if (!other.canEqual((Object) this)) return false;
    if (!super.equals(o)) return false;
    Object this$random = this.random;
    Object other$random = other.random;
    if (this$random == null ? other$random != null : !this$random.equals(other$random)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof ThreadShowName;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = super.hashCode();
    Object $random = this.random;
    result = result * PRIME + ($random == null ? 43 : $random.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "ThreadShowName(random=" + this.random + ")";
  }
}
