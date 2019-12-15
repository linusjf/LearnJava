package similarity;

import info.debatty.java.stringsimilarity.MetricLCS;

public enum MetricsLCSTest {
  ;
  public static void main(String[] args) {
    MetricLCS lcs = new MetricLCS();

    String s1 = "ABCDEFG";
    String s2 = "ABCDEFHJKL";
    System.out.println("\nMetricsLCSTest: \n");

    // LCS: ABCDEF => length = 6
    // longest = s2 => length = 10
    // => 1 - 6/10 = 0.4
    System.out.println(lcs.distance(s1, s2));

    // LCS: ABDF => length = 4
    // longest = ABDEF => length = 5
    // => 1 - 4 / 5 = 0.2
    System.out.println(lcs.distance("ABDEF", "ABDIF"));
  }
}
