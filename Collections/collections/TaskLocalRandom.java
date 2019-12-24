package collections;

import static java.lang.System.out;

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements Runnable {
  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  @Override
  public void run() {
    StringBuilder sb = new StringBuilder(40);
    sb.append(Thread.currentThread().getName())
        .append(" with priority ")
        .append(Thread.currentThread().getPriority())
        .append(": %d %n");
    String str = sb.toString();
    for (int i = 0; i < 10; i++) out.printf(str, ThreadLocalRandom.current().nextInt(10));
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    Thread[] threads = {
        new Thread(new TaskLocalRandom()),
        new Thread(new TaskLocalRandom()),
        new Thread(new TaskLocalRandom()),
    };
    ThreadLocalRandom tlr = ThreadLocalRandom.current();
    for (Thread t : threads) t.setPriority(tlr.nextInt(10) + 1);
    for (Thread t : threads) t.start();
  }
}
