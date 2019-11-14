package lsh;

import info.debatty.java.lsh.MinHash;
import java.util.TreeSet;

public enum MinHashExample {
  ;

  public static void main(String[] args) {
    // Initialize the hash function for an similarity error of 0.1
    // For sets built from a dictionary of 5 items
    MinHash minhash = new MinHash(0.1, 5);

    // Sets can be defined as an vector of booleans:
    // [1 0 0 1 0]
    boolean[] vector1 = {true, false, false, true, false};
    int[] sig1 = minhash.signature(vector1);

    // Or as a set of integers:
    // set2 = [1 0 1 1 0]
    TreeSet<Integer> set2 = new TreeSet<>();
    set2.add(0);
    set2.add(2);
    set2.add(3);
    System.out.println(set2.size());
    int[] sig2 = minhash.signature(set2);

    System.out.println("Signature similarity: " + minhash.similarity(sig1, sig2));
    System.out.println(
        "Real similarity (Jaccard index)"
            + MinHash.jaccardIndex(MinHash.convert2Set(vector1), set2));
  }
}
