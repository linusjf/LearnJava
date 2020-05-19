package threads;

import java.util.Objects;

public class QueueObject {
  private boolean isNotified;

  public void doWait() throws InterruptedException {
    synchronized (this) {
      while (!isNotified) this.wait();
      this.isNotified = false;
    }
  }

  public void doNotify() {
    synchronized (this) {
      this.isNotified = true;
      this.notifyAll();
    }
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public int hashCode() {
    return Objects.hash(super.hashCode(), isNotified);
  }

  @Override
  public boolean equals(Object o) {
    return this == o;
  }
}
