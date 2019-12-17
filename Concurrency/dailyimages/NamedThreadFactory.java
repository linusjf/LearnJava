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
    ThreadFactory factory =
        Executors.defaultThreadFactory();
    Thread t = factory.newThread(r);
    t.setName(name + "-" + count);
    System.out.println("New thread " + t);
    return t;
  }
}
