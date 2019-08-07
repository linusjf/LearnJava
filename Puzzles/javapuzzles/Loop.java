package javapuzzles;

public enum Loop {
  ;
  public static void main(String[] args) {
    int[][] tests = {{6, 5, 4, 3, 2, 1}, {1, 2}, {1, 2, 3}, {1, 2, 3, 4}, {1}};
    int successCount = 0;
    try {
      int i = 0;
      while (true) {
        if (thirdElementIsThree(tests[i++]))
          successCount++;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      // No more tests to process
      System.err.println("");
    }
    System.out.println(successCount);
    altMain(args);
  }

  private static boolean thirdElementIsThree(int... a) {
    // return a.length >= 3 & a[2] == 3;
    return a.length >= 3 && a[2] == 3;
  }

  public static void altMain(String... args) {
    int[][] tests = {{6, 5, 4, 3, 2, 1}, {1, 2}, {1, 2, 3}, {1, 2, 3, 4}, {1}};
    int successCount = 0;
    for (int[] test: tests)
      if (thirdElementIsThree(test))
        successCount++;
    System.out.println(successCount);
  }
}
