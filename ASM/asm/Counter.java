package asm;

@SuppressWarnings("PMD")
public class Counter {
  private int i = 0;
  private final Object lock = new Object();

  public int increment() {
    synchronized (lock) {
      return ++i;
    }
  }
}
