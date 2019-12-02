package pmdtests;

import java.util.Random;

public enum ArrayCopy {
  ;
  private static String[] words = {
    "the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main"
  };

  private static Random random = new Random();

  private static String[][] document = new String[10][100];
  
  public static void main(String[] args) {

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 100; j++) 
        document[i][j] = words[random.nextInt(words.length)];
    }
  }
}
