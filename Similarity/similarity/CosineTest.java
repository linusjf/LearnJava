package similarity;

import info.debatty.java.stringsimilarity.Cosine;
import java.util.Map;

/** Example of computing cosine similarity with pre-computed profiles. */
public enum CosineTest {
  ;

  public static void main(String[] args) {
    String s1 = "My first string";
    String s2 = "My other string...";

    // Let's work with sequences of 2 characters...
    Cosine cosine = new Cosine(2);

    // Pre-compute the profile of strings
    Map<String, Integer> profile1 = cosine.getProfile(s1);
    Map<String, Integer> profile2 = cosine.getProfile(s2);

    System.out.println("\nCosine: \n");

    // Prints 0.516185
    System.out.println(cosine.similarity(profile1, profile2));
  }
}
