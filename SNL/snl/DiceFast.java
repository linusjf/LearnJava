package snl;

import java.util.concurrent.ThreadLocalRandom;

// https://www.jstatsoft.org/article/view/v008i14
public class DiceFast implements Dice {
  private int x = ThreadLocalRandom.current().nextInt();
  private int y = ThreadLocalRandom.current().nextInt();
  private int z = ThreadLocalRandom.current().nextInt();
  private int w = ThreadLocalRandom.current().nextInt();
  public int roll() {
    final int tmp = x ^ (x << 15);
    x = y;
    y = z;
    z = w;
    w = (w ^ (w >> 21)) ^ (tmp ^ (tmp >> 4));
    return mod(w, 6) + 1;
  }

  protected int mod(int a, int div) {
    int result = a % div;
    if (result < 0)
      return -result;
    return result;
  }
}
