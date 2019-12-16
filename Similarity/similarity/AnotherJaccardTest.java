package similarity;

import info.debatty.java.stringsimilarity.Jaccard;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public enum AnotherJaccardTest {
  ;

  public static void main(String... args) {
    for (int i = 2; i <= 10; ++i) {
      Jaccard jac = new Jaccard(i);

      System.out.printf("%nAnother Jaccard: %d %n", i);

      System.out.println("\nSimilarity:\n");

      System.out.println(
          jac.similarity("Who was the first king of Poland", "Who was the first ruler of Poland"));

      System.out.println(
          jac.similarity("Who was the first king of Poland", "Who was the last pharaoh of Egypt"));

      System.out.println(
          jac.similarity("Who was the first ruler of Poland", "Who was the last pharaoh of Egypt"));
    }
  }
}
