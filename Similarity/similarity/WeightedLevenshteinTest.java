package similarity;

import info.debatty.java.stringsimilarity.CharacterSubstitutionInterface;
import info.debatty.java.stringsimilarity.WeightedLevenshtein;
import org.javatuples.Pair;

public enum WeightedLevenshteinTest {
  ;



  public static void main(String... args) {
    WeightedLevenshtein wl =
        new WeightedLevenshtein(new CharacterSubstitutionInterface() {
          public double cost(char c1, char c2) {

            // The cost for substituting 't' and 'r' is considered
            // smaller as these 2 are located next to each other
            // on a keyboard
            if (c1 == 't' && c2 == 'r') {
              return 0.5;
            }
            if (c1 == 'q' && c2 == 'w') {
              return 0.5;
            }
            if (c1 == 'w' && c2 == 'e') {
              return 0.5;
            }

            // For most cases, the cost of substituting 2 characters
            // is 1.0
            return 1.0;
          }
        });

    System.out.println(wl.distance("String1", "Srring2"));
  }
}
