package javapuzzles;

public enum Laches {
  ;

  public static void main(String... args) {
    int i = 0x01;
    int j = 0x10;
    int k = i | j;
    System.out.println(k ^ i);
  }
}
