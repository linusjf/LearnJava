package javapuzzles;

@SuppressWarnings("PMD.DontUseFloatTypeForLoopIndices")
public enum Count {
  ;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String... args) {
    final int start = 2_000_000_000;
    int count = 0;
    for (float f = start; f < start + 50; f++) count++;
    System.out.println(count);
    for (float f = start; f < start + 64; f++) count++;
    System.out.println(count);
  }
}
