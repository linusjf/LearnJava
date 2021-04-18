package cryptic;

import java.util.Random;

/** First line. https://metebalci.com/blog/everything-about-javas-securerandom/ */
public final class DeconstructRandom {

  private static final Random RANDOM = new Random();

  private DeconstructRandom() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {

    // we are going to guess what is going to be third random
    final int firstRandom = RANDOM.nextInt();
    final int secondRandom = RANDOM.nextInt();

    // these are constants from the java.util.Random source code
    // multiplier
    final long a = 0x5DEECE66DL;
    // addend
    final long c = 0xBL;

    // 16 because 48 - 32 = 16
    // java.util.Random generates 48-bit random numbers
    final long startOfSeed = (long)firstRandom << 16;

    long nextSeed = 0;

    // we do not know the last 16-bits, so we try all
    for (int i = 0; i < 0xFFFF; i++) {
      // this algorithm is same as java.util.Random
      final long nextSeedGuess = ((startOfSeed + i) * a + c) & ((1L << 48) - 1);
      final long secondRandomGuess = (int)(nextSeedGuess >>> 16);

      if (secondRandomGuess == secondRandom) {
        nextSeed = nextSeedGuess;
        break;
      }
    }

    if (Math.abs(nextSeed) > 0) {

      System.out.println("seed found");

      // this algorithm is same as java.util.Random
      nextSeed = (nextSeed * a + c) & ((1L << 48) - 1);
      final int thirdRandomGuess = (int)(nextSeed >>> 16);

      final int thirdRandom = RANDOM.nextInt();

      if (thirdRandomGuess == thirdRandom)
        System.out.println("guessed third random correctly");
      else
        System.out.println("wrong third random guess");
    } else
      System.out.println("seed not found");
  }
}
