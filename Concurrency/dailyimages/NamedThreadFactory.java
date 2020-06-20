package dailyimages;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
  private final String name;
  private int count;

  public NamedThreadFactory(String name) {
    this.name = name;
  }

  @Override
  public Thread newThread(Runnable r) {
    count++;
    ThreadFactory factory = Executors.defaultThreadFactory();
    Thread t = factory.newThread(r);
    t.setName(name + "-" + count);
    System.out.println("New thread " + t);
    return t;
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof NamedThreadFactory)) return false;
    NamedThreadFactory other = (NamedThreadFactory) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$name = this.name;
    Object other$name = other.name;
    if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
    if (this.count != other.count) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof NamedThreadFactory;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $name = this.name;
    result = result * PRIME + ($name == null ? 43 : $name.hashCode());
    result = result * PRIME + this.count;
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "NamedThreadFactory(name=" + this.name + ", count=" + this.count + ")";
  }
}
