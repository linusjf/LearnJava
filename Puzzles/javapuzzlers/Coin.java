package javapuzzlers;

import java.util.Locale;
import java.util.Random;

public enum Coin {
  HEADS,
  TAILS;
  private static Random rnd = new Random();

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public String toString() {
    return name().toLowerCase(Locale.getDefault());
  }

  public static Coin flip() {
    return rnd.nextBoolean() ? HEADS : TAILS;
  }

@SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] args) {
    System.out.println(flip());
  }
}
