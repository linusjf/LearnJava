package javapuzzles;

public enum Overflow {
  ;

  public static void main(String[] args) {
    int x = -2_000_000_000;
    int z = 2_000_000_000;
    System.out.println(x - z);

    int y = 1024 * 1024 * 1024;
    System.out.println(y);
    y = y * 2;
    System.out.println(y);
    y = y * 2;
    System.out.println(y);
  }
}
