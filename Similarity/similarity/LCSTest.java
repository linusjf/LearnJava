package similarity;

import info.debatty.java.stringsimilarity.LongestCommonSubsequence;

public enum LCSTest {
  ;
  public static void main(String[] args) {
    LongestCommonSubsequence lcs = new LongestCommonSubsequence();

    System.out.println("\nLongest Common Subsequence: \n");

    // Will produce 4.0
    System.out.println(lcs.distance("AGCAT", "GAC"));

    // Will produce 1.0
    System.out.println(lcs.distance("AGCAT", "AGCT"));
  }
}
