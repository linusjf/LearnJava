package javapuzzles;

@SuppressWarnings("PMD.ShortClassName")
public enum Mod {
  ;
  private static final int MODULUS = 3;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    int[] histogram = new int[MODULUS];

    // Iterate over all ints (Idiom from Puzzle 26)
    int i = Integer.MIN_VALUE;
    try {
      do {
        histogram[Math.abs(i) % MODULUS]++;
      } while (i++ != Integer.MAX_VALUE);
      for (int j = 0; j < MODULUS; j++)
        System.out.print(histogram[j] + " ");
    } catch (ArrayIndexOutOfBoundsException e) {
      System.err.println(e);
    }
    altMain(args);
  }

  private static int mod(int i, int modulus) {
    int result = i % modulus;
    return result < 0 ? result + modulus : result;
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void altMain(String... args) {
    int[] histogram = new int[MODULUS];

    // Iterate over all ints (Idiom from Puzzle 26)
    int i = Integer.MIN_VALUE;
    do {
      histogram[mod(i, MODULUS)]++;
    } while (i++ != Integer.MAX_VALUE);
    for (int j = 0; j < MODULUS; j++)
      System.out.print(histogram[j] + " ");
  }
}
