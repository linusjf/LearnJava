package unsafe;

public class CounterClient implements Runnable {
  private Counter c;
  private long num;

  public CounterClient(Counter c, long num) {
    this.c = c;
    this.num = num;
  }

  @Override
  public void run() {
    for (long i = 0; i < num; i++)
      c.increment();
  }
}
