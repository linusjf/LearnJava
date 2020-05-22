package unsafe;

public class StupidCounter implements Counter {
  private long counter;

  @Override
  public void increment() {
    ++counter;
  }

  @Override
  public long get() {
    return counter;
  }
}
