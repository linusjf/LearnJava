package javapuzzles;

public enum DoubleXX {
  ;

  public static void main(String... args) {
    char x = 'X';
    char i = 0;
    int j = 0;
    System.out.print(true ? x : 0);
    System.out.print(false ? i : x);
    System.out.println();
    System.out.print(true ? x : 0);
    System.out.print(false ? j : x);
  }
}
