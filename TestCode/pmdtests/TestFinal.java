package pmdtests;

import java.math.BigInteger;
import java.util.Random;

public class TestFinal {
  public TestFinal() {
    Random random = new Random();
    BigInteger e = BigInteger.probablePrime(Integer.MAX_VALUE / 2, random);

    while (random.nextBoolean()) e = e.add(BigInteger.ONE);
  }
}
