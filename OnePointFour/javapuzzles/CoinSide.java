package javapuzzles;

import java.util.Random;

public abstract class CoinSide { // NOPMD
  private static Random rnd = new Random();

  public static CoinSide flip() {
    return rnd.nextBoolean() ? (CoinSide) Heads.INSTANCE : Tails.INSTANCE;
  }

  public static void main(String[] args) {
    System.out.println(flip());
  }
}

final class Heads extends CoinSide {
  public static final Heads INSTANCE = new Heads();

  private Heads() {
    super();
  }

  public String toString() { // NOPMD
    return "heads";
  }
}

final class Tails extends CoinSide {
  public static final Tails INSTANCE = new Tails();

  private Tails() {
    super();
  }

  public String toString() { // NOPMD
    return "tails";
  }
}
