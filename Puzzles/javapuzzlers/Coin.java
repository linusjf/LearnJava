package javapuzzlers;

import java.util.Random;
import java.util.Locale;

public enum Coin {
  HEADS,
  TAILS;
  
  private static Random rnd = new Random();

  @Override
  public String toString() {
    return name().toLowerCase(Locale.getDefault());
  }

  public static Coin flip() {
    return rnd.nextBoolean() ? HEADS : TAILS;
  }

  public static void main(String[] args) {
    System.out.println(flip());
  }
}
