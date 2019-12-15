package collections;

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements Runnable {

  public TaskLocalRandom() {
    ThreadLocalRandom.current();
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  @Override
  public void run() {
    StringBuilder sb = new StringBuilder(40);
    sb.append(Thread.currentThread().getName())
      .append(" with priority %d: %d \n");
    String str = sb.toString();
    for (int i = 0; i < 10; i++) System.out.printf(
      str,
      Thread.currentThread().getPriority(),
      ThreadLocalRandom.current().nextInt(10)
    );
  }

  public static void main(String[] args) {
    Thread[] threads = {
      new Thread(new TaskLocalRandom()),
      new Thread(new TaskLocalRandom()),
      new Thread(new TaskLocalRandom()),
    };
    for (Thread t : threads) {
      t.setPriority(ThreadLocalRandom.current().nextInt(10) + 1);
      t.start();
    }
  }
}
