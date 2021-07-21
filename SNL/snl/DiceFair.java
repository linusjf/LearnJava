package snl;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DiceFair implements Dice {
  private final Random random;

  public DiceFair() {
    this(ThreadLocalRandom.current());
  }

  public DiceFair(Random random) {
    this.random = random;
  }

  public int roll() {
    return random.nextInt(1, 7);
  }
}
