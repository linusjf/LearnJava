package javapuzzles;

public enum Loopy {
  ;
  public static void main(String... args) {

    int[] n = new int[] {2, 5, 6};
    for (int i: n)
      i += 2;
    for (int i: n)
      System.err.print(++i + " ");
  }
}
