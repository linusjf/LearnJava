package unsafe;

public class CounterClient implements Runnable {
  private final Counter c;
  private final long num;

  public CounterClient(Counter c, long num) {
    this.c = c;
    this.num = num;
  }

  @Override
  public void run() {
    for (long i = 0; i < num; i++) c.increment();
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "CounterClient(c=" + this.c + ", num=" + this.num + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof CounterClient)) return false;
    CounterClient other = (CounterClient) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$c = this.c;
    Object other$c = other.c;
    if (this$c == null ? other$c != null : !this$c.equals(other$c)) return false;
    if (this.num != other.num) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof CounterClient;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $c = this.c;
    result = result * PRIME + ($c == null ? 43 : $c.hashCode());
    long $num = this.num;
    result = result * PRIME + (int) ($num >>> 32 ^ $num);
    return result;
  }
}
