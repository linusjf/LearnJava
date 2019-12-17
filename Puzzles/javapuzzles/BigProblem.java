package javapuzzles;

import java.math.BigInteger;

@SuppressWarnings("PMD")
public enum BigProblem {
  ;

  public static void main(String[] args) {
    BigInteger fiveThousand = new BigInteger("5000");
    BigInteger fiftyThousand = new BigInteger("50000");
    BigInteger fiveHundredThousand =
        new BigInteger("500000");
    BigInteger total = BigInteger.ZERO;
    total.add(fiveThousand);
    total.add(fiftyThousand);
    total.add(fiveHundredThousand);
    System.out.println(total);
    altMain(args);
  }

  public static void altMain(String... args) {
    BigInteger fiveThousand = new BigInteger("5000");
    BigInteger fiftyThousand = new BigInteger("50000");
    BigInteger fiveHundredThousand =
        new BigInteger("500000");
    BigInteger total = BigInteger.ZERO;
    total = total.add(fiveThousand);
    total = total.add(fiftyThousand);
    total = total.add(fiveHundredThousand);
    System.out.println(total);
  }
}
