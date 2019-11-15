package lsh;

import info.debatty.java.lsh.SuperBit;
import java.util.Random;

public enum SuperBitSig {
  ;

  public static void main(String[] args) {

    int n = 10;

    // Initialize Super-Bit
    SuperBit sb = new SuperBit(n);

    Random rand = new Random();
    double[] v1 = new double[n];
    double[] v2 = new double[n];
    for (int i = 0; i < n; i++) {
      v1[i] = rand.nextInt();
      v2[i] = rand.nextInt();
    }

    boolean[] sig1 = sb.signature(v1);
    boolean[] sig2 = sb.signature(v2);

    System.out.println("Signature (estimated) similarity: "
                       + sb.similarity(sig1, sig2));
    System.out.println("Real (cosine) similarity: "
                       + SuperBit.cosineSimilarity(v1, v2));
  }
}
