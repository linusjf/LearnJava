package javapuzzlers;

import java.util.Random;

public enum Coin {
  HEADS,
  TAILS;
  
  private static Random rnd = new Random();

  public String toString() {
    return name().toLowerCase();
  }

  public static Coin flip() {
    return rnd.nextBoolean() ? HEADS : TAILS;
  }

  public static void main(String[] args) {
    System.out.println(flip());
  }
}
