package similarity;

import info.debatty.java.stringsimilarity.Damerau;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public enum DamerauLevenshteinTest {
  ;

  public static void main(String[] args) {
    Damerau d = new Damerau();

    System.out.println("\nDamerau: \n");

    // 1 substitution
    System.out.println(d.distance("ABCDEF", "ABDCEF"));

    // 2 substitutions
    System.out.println(d.distance("ABCDEF", "BACDFE"));

    // 1 deletion
    System.out.println(d.distance("ABCDEF", "ABCDE"));
    System.out.println(d.distance("ABCDEF", "BCDEF"));
    System.out.println(d.distance("ABCDEF", "ABCGDEF"));

    // All different
    System.out.println(d.distance("ABCDEF", "POIU"));
  }
}
