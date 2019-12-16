package similarity;

import info.debatty.java.stringsimilarity.OptimalStringAlignment;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public enum OptimalStringTest {
  ;

  public static void main(String[] args) {
    OptimalStringAlignment osa = new OptimalStringAlignment();

    System.out.println("\nOptimal String Alignment: \n");

    // 1 substitution
    System.out.println(osa.distance("ABCDEF", "ABDCEF"));

    // 2 substitutions
    System.out.println(osa.distance("ABCDEF", "BACDFE"));

    // 1 deletion
    System.out.println(osa.distance("ABCDEF", "ABCDE"));
    System.out.println(osa.distance("ABCDEF", "BCDEF"));
    System.out.println(osa.distance("ABCDEF", "ABCGDEF"));

    // All different
    System.out.println(osa.distance("ABCDEF", "POIU"));
    System.out.println(osa.distance("CA", "ABC"));
  }
}
