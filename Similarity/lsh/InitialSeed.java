package lsh;

import info.debatty.java.lsh.MinHash;
import java.util.Random;

public enum InitialSeed {
  ;

  public static void main(String[] args) {
    // Initialize two minhash objects, with the same seed
    int signatureSize = 20;
    int dictionarySize = 100;
    long initialSeed = 123_456;

    MinHash mh = new MinHash(signatureSize, dictionarySize, initialSeed);
    MinHash mh2 = new MinHash(signatureSize, dictionarySize, initialSeed);

    // Create a single vector of size dictionary_size
    Random r = new Random();
    boolean[] vector = new boolean[dictionarySize];
    for (int i = 0; i < dictionarySize; i++) {
      vector[i] = r.nextBoolean();
    }

    // The two minhash objects will produce the same signature
    println(mh.signature(vector));
    println(mh2.signature(vector));
  }

  static void println(final int... array) {
    System.out.print("[");
    for (int v: array) {
      System.out.print(v + " ");
    }
    System.out.println("]");
  }
}
