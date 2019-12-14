package similarity;

import info.debatty.java.stringsimilarity.SorensenDice;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public enum SorensenDiceTest {
  ;

  public static void main(String... args) {
    SorensenDice sd = new SorensenDice();

    System.out.println("\nSorensen Dice: \n");

    System.out.println("Distance:\n");

    // 1 substitution
    System.out.println(sd.distance("ABCDEF", "ABDCEF"));

    // 2 substitutions
    System.out.println(sd.distance("ABCDEF", "BACDFE"));

    // 1 deletion
    System.out.println(sd.distance("ABCDEF", "ABCDE"));
    System.out.println(sd.distance("ABCDEF", "BCDEF"));
    System.out.println(sd.distance("ABCDEF", "ABCGDEF"));

    // All different
    System.out.println(sd.distance("ABCDEF", "POIU"));
    System.out.println(sd.distance("CA", "ABC"));

    System.out.println("\nSimilarity:\n");

    // 1 substitution
    System.out.println(sd.similarity("ABCDEF", "ABDCEF"));

    // 2 substitutions
    System.out.println(sd.similarity("ABCDEF", "BACDFE"));

    // 1 deletion
    System.out.println(sd.similarity("ABCDEF", "ABCDE"));
    System.out.println(sd.similarity("ABCDEF", "BCDEF"));
    System.out.println(sd.similarity("ABCDEF", "ABCGDEF"));

    // All different
    System.out.println(sd.similarity("ABCDEF", "POIU"));
    System.out.println(sd.similarity("CA", "ABC"));
  }
}
