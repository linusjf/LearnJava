package pmdtests;

import java.util.Random;

@SuppressWarnings("checkstyle:magicnumber")
public enum ArrayCopy {
  ;
  private static String[] words = {
    "the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main",
  };

  private static Random random = new Random();

  private static String[][] document = new String[10][100];

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 100; j++)
        // words.length should not be flagged for LOD
        document[i][j] = words[random.nextInt(words.length)];
    }
  }
}
