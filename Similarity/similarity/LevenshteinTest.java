package similarity;

import info.debatty.java.stringsimilarity.Levenshtein;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public enum LevenshteinTest {
  ;

  public static void main(String[] args) {
    Levenshtein l = new Levenshtein();
    System.out.println("\nLevenshtein: \n");

    System.out.println(l.distance("My string", "My $tring"));
    System.out.println(l.distance("column", "columns"));
    System.out.println(l.distance("col", "col1"));
    System.out.println(l.distance("col", "col12"));
    System.out.println(l.distance("col", "col123"));
  }
}
