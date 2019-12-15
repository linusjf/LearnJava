package javapuzzles;

public enum IPlusPlus {
  ;
  public static void main(String... args) {
    for (int i = 0; i < 2; i = iplusplus(i)) System.out.println("t" + i);
  }

  @SuppressWarnings("PMD.AvoidReassigningParameters")
  private static int iplusplus(int i) {
    System.out.println("t" + i);
    return ++i;
  }
}
