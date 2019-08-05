package javapuzzles;

public enum Shifty {
  ;
  private static final int INT_BITS = 32;

  public static void main(String... args) {
    int i = 0;
    while (-1 << i != 0) {
      int j = -1 << i;
      System.out.printf("%d %d %n", j, i);
      i++;
      if (i > INT_BITS)
        break;
    }
    System.out.println(i);
    altMain(args);
  }

  public static void altMain(String... args) {
    int distance = 0;
    for (int val = -1; val != 0; val <<= 1) {
      System.out.printf("%d %d %n", val, distance);
      distance++;
    }
    System.out.println(distance);
    System.out.println(-2_147_483_648 << 1);
    System.out.println(-2_147_483_648 << 32);
  }
}
