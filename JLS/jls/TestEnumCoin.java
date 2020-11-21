package jls;

public final class TestEnumCoin {
  private TestEnumCoin() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String[] args) {
    for (Coin c: Coin.values())
      System.out.println(c + "\t\t" + c.value() + "\t" + color(c));
  }

  enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private final int value;

    Coin(int value) {
      this.value = value;
    }

    public int value() {
      return value;
    }
  }

  enum CoinColor { 
    COPPER, 
    NICKEL, 
    SILVER 
  }

  static CoinColor color(Coin c) {
    switch (c) {
      case PENNY:
        return CoinColor.COPPER;
      case NICKEL:
        return CoinColor.NICKEL;
      case DIME:
      case QUARTER:
        return CoinColor.SILVER;
      default:
        throw new AssertionError("Unknown coin: " + c);
    }
  }
}
