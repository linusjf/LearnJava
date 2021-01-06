package javapuzzlers;

import java.util.Random;

public final class CoinSide {
  public static final CoinSide HEADS = new CoinSide("heads");
  public static final CoinSide TAILS = new CoinSide("tails");
  private static Random rnd = new Random();
  private final String name;

  private CoinSide(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  public static CoinSide flip() {
    return rnd.nextBoolean() ? HEADS : TAILS;
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] args) {
    System.out.println(flip());
  }
}
