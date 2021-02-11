package asm;

public final class CounterMain {

  private static final int MAX_INC = 10_000_000;

  private CounterMain() {
    throw new AssertionError("Private constructor");
  }

  public static void main(String... args) throws InterruptedException {
    Counter c = new Counter();
    Runnable r = () -> {
      for (int i = 0; i < MAX_INC; i++) {
        c.increment();
      }
    };
    Thread t1 = new Thread(r);
    Thread t2 = new Thread(r);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    int disc = 2 * MAX_INC - c.increment() + 1;
    System.out.println("Discrepancy: " + disc);
  }
}
