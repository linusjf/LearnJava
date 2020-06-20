package threads;

import java.util.Objects;

public class QueueObject {
  private boolean isNotified;
  private final Object obj = new Object();

  public void doWait() throws InterruptedException {
    synchronized (obj) {
      while (!isNotified) obj.wait(100);
      this.isNotified = false;
    }
  }

  public void doNotify() {
    synchronized (obj) {
      this.isNotified = true;
      obj.notifyAll();
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

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "QueueObject(isNotified=" + this.isNotified + ")";
  }
}
