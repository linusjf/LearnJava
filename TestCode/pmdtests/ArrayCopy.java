package pmdtests;

import java.util.Random;

public enum ArrayCopy {
  ;
  private static String[] words = {"the",
                            "hello",
                            "goodbye",
                            "packt",
                            "java",
                            "thread",
                            "pool",
                            "random",
                            "class",
                            "main"};

  public static void main(String[] args) {
    String[][] document = new String[10][100];
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 100; j++) {
        int index = random.nextInt(words.length);
        document[i][j] = words[index];
      }
    }
  }
}
