package javapuzzles;

public enum Wraparound {
  ;

  public static void main(String... args) {
    method(Integer.MAX_VALUE + 1, Integer.MIN_VALUE - 1);
    method(Long.MAX_VALUE + 1, Long.MIN_VALUE - 1);
  }

  public static void method(int max, int min) {
    System.out.println("No");
    System.out.printf("%d %d\n", max, min);
  }

  public static void method(long max, long min) {
    System.out.println("Yes");
    System.out.printf("%d %d\n", max, min);
  }
}
