package similarity;

import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public enum NormalizedLevenshteinTest {
  ;

  public static void main(String[] args) {
    NormalizedLevenshtein nl = new NormalizedLevenshtein();

    System.out.println("\nNormalized Levenshtein: \n");
    System.out.println(nl.distance("My string", "My $tring"));
    System.out.println(nl.distance("column", "columns"));
    System.out.println(nl.distance("col", "col1"));
    System.out.println(nl.distance("col", "col12"));
    System.out.println(nl.distance("col", "col123"));
    System.out.println(1 - nl.distance("My string", "My $tring"));
    System.out.println(1 - nl.distance("column", "columns"));
    System.out.println(1 - nl.distance("col", "col1"));
    System.out.println(1 - nl.distance("col", "col12"));
    System.out.println(1 - nl.distance("col", "col123"));
  }
}
