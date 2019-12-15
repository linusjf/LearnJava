package similarity;

import info.debatty.java.stringsimilarity.JaroWinkler;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public enum JaroWinklerTest {
  ;
  public static void main(String[] args) {
    JaroWinkler jw = new JaroWinkler();

    System.out.println("\nJaro-Winkler: \n");

    System.out.println("\nSimilarity: \n");

    // substitution of s and t
    System.out.println(jw.similarity("My string", "My tsring"));

    // substitution of s and n
    System.out.println(jw.similarity("My string", "My ntrisg"));

    // 1 substitution
    System.out.println(jw.similarity("ABCDEF", "ABDCEF"));

    // 2 substitutions
    System.out.println(jw.similarity("ABCDEF", "BACDFE"));

    // 1 deletion
    System.out.println(jw.similarity("ABCDEF", "ABCDE"));
    System.out.println(jw.similarity("ABCDEF", "BCDEF"));
    System.out.println(jw.similarity("ABCDEF", "ABCGDEF"));

    // All different
    System.out.println(jw.similarity("ABCDEF", "POIU"));
    System.out.println(jw.similarity("column", "columns"));
    System.out.println(jw.similarity("col", "col1"));
    System.out.println(jw.similarity("col", "col12"));
    System.out.println(jw.similarity("col", "col123"));

    System.out.println("\nDistance: \n");

    // substitution of s and t
    System.out.println(1 - jw.similarity("My string", "My tsring"));

    // substitution of s and n
    System.out.println(1 - jw.similarity("My string", "My ntrisg"));

    // 1 substitution
    System.out.println(1 - jw.similarity("ABCDEF", "ABDCEF"));

    // 2 substitutions
    System.out.println(1 - jw.similarity("ABCDEF", "BACDFE"));

    // 1 deletion
    System.out.println(1 - jw.similarity("ABCDEF", "ABCDE"));
    System.out.println(1 - jw.similarity("ABCDEF", "BCDEF"));
    System.out.println(1 - jw.similarity("ABCDEF", "ABCGDEF"));

    // All different
    System.out.println(1 - jw.similarity("ABCDEF", "POIU"));
    System.out.println(1 - jw.similarity("column", "columns"));
    System.out.println(1 - jw.similarity("col", "col1"));
    System.out.println(1 - jw.similarity("col", "col12"));
    System.out.println(1 - jw.similarity("col", "col123"));
  }
}
