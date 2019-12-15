package javapuzzles;

public enum Symposium {
  ;
  @SuppressWarnings("PMD.UselessParentheses")
  public static void main(String... args) {
    int i = 0;
    int s = (++i) + (i--) + i;
    System.out.println(s);
    i = 0;
    s = (++i) + ((i--) + i);
    System.out.println(s);
    i = 0;
    s = ++i + i-- + i;
    System.out.println(s);
  }
}
