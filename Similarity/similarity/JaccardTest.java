package similarity;

import info.debatty.java.stringsimilarity.Jaccard;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public enum JaccardTest {
  ;

  public static void main(String... args) {
    Jaccard jac = new Jaccard();

    System.out.println("\nJaccard: \n");

    System.out.println("Distance:\n");

    // 1 substitution
    System.out.println(jac.distance("ABCDEF", "ABDCEF"));

    // 2 substitutions
    System.out.println(jac.distance("ABCDEF", "BACDFE"));

    // 1 deletion
    System.out.println(jac.distance("ABCDEF", "ABCDE"));
    System.out.println(jac.distance("ABCDEF", "BCDEF"));
    System.out.println(jac.distance("ABCDEF", "ABCGDEF"));

    // All different
    System.out.println(jac.distance("ABCDEF", "POIU"));
    System.out.println(jac.distance("CA", "ABC"));

    System.out.println("\nSimilarity:\n");

    // 1 substitution
    System.out.println(jac.similarity("ABCDEF", "ABDCEF"));

    // 2 substitutions
    System.out.println(jac.similarity("ABCDEF", "BACDFE"));

    // 1 deletion
    System.out.println(jac.similarity("ABCDEF", "ABCDE"));
    System.out.println(jac.similarity("ABCDEF", "BCDEF"));
    System.out.println(jac.similarity("ABCDEF", "ABCGDEF"));

    // All different
    System.out.println(jac.similarity("ABCDEF", "POIU"));
    System.out.println(jac.similarity("CA", "ABC"));
  }
}
