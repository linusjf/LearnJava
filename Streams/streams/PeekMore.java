package streams;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public enum PeekMore {
  ;
  private static final AtomicBoolean FLAG = new AtomicBoolean(false);

  private static void setFlagIfGreaterThanZero(int val) {
    if (val > 0) {
      FLAG.set(true);
    }
  }

  public static void main(String[] args) {
    FLAG.set(false);

    // PeekMore 1
    IntStream.range(0, 10).peek(PeekMore::setFlagIfGreaterThanZero).findFirst();

    System.out.println(FLAG.get());
    FLAG.set(false);

    // PeekMore 2
    IntStream.range(0, 10)
        .peek(PeekMore::setFlagIfGreaterThanZero)
        .sorted()
        .findFirst();

    System.out.println(FLAG.get());
    FLAG.set(false);

    // PeekMore 3
    IntStream.range(0, 10)
        .boxed()
        .peek(PeekMore::setFlagIfGreaterThanZero)
        .sorted()
        .findFirst();

    System.out.println(FLAG.get());
    FLAG.set(false);

    // PeekMore 4
    IntStream.range(0, 10)
        .peek(PeekMore::setFlagIfGreaterThanZero)
        .filter(x -> x == 0)
        .toArray();

    // No find first. so returns last flag value set.
    System.out.println(FLAG.get());
  }
}
